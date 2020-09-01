package com.example.activiti.business.listener;

import com.example.activiti.business.constant.ActivitiConstant;
import com.example.activiti.business.context.ActivitiContext;
import com.example.activiti.business.entity.MultiInstances;
import com.example.activiti.business.service.MultiInstancesService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/8/28
 * @description
 */
@Service("signListener")
@Slf4j
public class SignListener implements TaskListener {

    @Resource
    private MultiInstancesService multiInstancesService;

    @Override
    public void notify(DelegateTask delegateTask) {
        if (EVENTNAME_COMPLETE.equals(delegateTask.getEventName())) {
            log.info("enter signListener...");
            String executionId = delegateTask.getExecutionId();

            // 根据流程key、任务节点获取多实例的完成条件
            String processKey = delegateTask.getProcessDefinitionId().split(":")[0];
            String nodeId = delegateTask.getTaskDefinitionKey();
            MultiInstances multiInstances = multiInstancesService.selectSelective(processKey, nodeId);
            String completeCondition = multiInstances.getCompleteCondition();
            completeCondition = completeCondition.replaceAll("\\$\\{", "").replaceAll("}", "");
            log.info("completeCondition:" + completeCondition);

            // 一票通过
            if (ActivitiConstant.ONE_VOTE_PASS.equals(completeCondition)) {
                log.info("一票通过");
                boolean pass = (boolean) delegateTask.getVariableInstanceLocal(ActivitiConstant.MULTI_INSTANCE_VARIABLE_KEY).getValue();
                if (!pass) {
                    RuntimeService runtimeService = ActivitiContext.getRuntimeService();
                    Integer nrOfCompletedInstances = (Integer) runtimeService.getVariable(executionId, "nrOfCompletedInstances");
                    Integer nrOfInstances = (Integer) runtimeService.getVariable(executionId, "nrOfInstances");
                    // 如果一个审批人完成了审批，进入到该监听时nrOfCompletedInstances的值还没有更新，因此需要+1
                    if ((nrOfCompletedInstances + 1) / nrOfInstances == 1) {
                        delegateTask.setVariable(ActivitiConstant.VARIABLE_KEY, ActivitiConstant.advice.ADVICE_REFUSE);
                    }
                } else {
                    delegateTask.setVariable(ActivitiConstant.VARIABLE_KEY, ActivitiConstant.advice.ADVICE_PASS);
                }
                return;
            }

            // 一票否决
            if (ActivitiConstant.ONE_PASS_REFUSE.equals(completeCondition)) {
                log.info("一票否决");
                boolean pass = (boolean) delegateTask.getVariableInstanceLocal(ActivitiConstant.MULTI_INSTANCE_VARIABLE_KEY).getValue();
                if (!pass) {
                    delegateTask.setVariable(ActivitiConstant.VARIABLE_KEY, ActivitiConstant.advice.ADVICE_REFUSE);
                } else {
                    RuntimeService runtimeService = ActivitiContext.getRuntimeService();
                    Integer nrOfCompletedInstances = (Integer) runtimeService.getVariable(executionId, "nrOfCompletedInstances");
                    Integer nrOfInstances = (Integer) runtimeService.getVariable(executionId, "nrOfInstances");
                    // 如果一个审批人完成了审批，进入到该监听时nrOfCompletedInstances的值还没有更新，因此需要+1
                    if ((nrOfCompletedInstances + 1) / nrOfInstances == 1) {
                        delegateTask.setVariable(ActivitiConstant.VARIABLE_KEY, ActivitiConstant.advice.ADVICE_PASS);
                    }
                }
                return;
            }

            // 其他完成条件，如只需要部分人处理(无论通过还是拒绝)即可
            // 不支持部分人通过的场景
            log.info("其他完成条件");
            RuntimeService runtimeService = ActivitiContext.getRuntimeService();
            Integer nrOfCompletedInstances = (Integer) runtimeService.getVariable(executionId, "nrOfCompletedInstances");
            Integer nrOfInstances = (Integer) runtimeService.getVariable(executionId, "nrOfInstances");
            if (judge(completeCondition, nrOfCompletedInstances, nrOfInstances)) {
                delegateTask.setVariable(ActivitiConstant.VARIABLE_KEY, ActivitiConstant.advice.ADVICE_PASS);
            }
        }
    }

    /**
     * 由流程定义的完成规则判断当前条件是否满足
     *
     * @param completeCondition      完成条件
     * @param nrOfCompletedInstances 已完成的实例
     * @param nrOfInstances          总实例
     * @return
     */
    public boolean judge(String completeCondition, int nrOfCompletedInstances, int nrOfInstances) {
        JexlContext jexlContext = new MapContext();
        // 如果一个审批人完成了审批，进入到该监听时nrOfCompletedInstances的值还没有更新，因此需要+1
        jexlContext.set("nrOfCompletedInstances", (float) nrOfCompletedInstances + 1);
        jexlContext.set("nrOfInstances", nrOfInstances);
        Expression e = new JexlEngine().createExpression(completeCondition);
        Boolean flag = (Boolean) e.evaluate(jexlContext);
        return flag;
    }
}
