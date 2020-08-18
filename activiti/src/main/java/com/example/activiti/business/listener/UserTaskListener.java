package com.example.activiti.business.listener;

import com.example.activiti.business.service.TestRoleService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/8/13
 * @description
 */
@Service("userTaskListener")
@Slf4j
public class UserTaskListener implements TaskListener {

    private static final long serialVersionUID = 3751958445937091039L;

    @Resource
    private TestRoleService testRoleService;

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();
        if (EVENTNAME_ASSIGNMENT.equals(eventName)) {
            // 任务配置处理人，比create先执行
            log.info("task assignment...");

            // 配置任务负责人
            /*String processKey = "sequential";
            int index = testRoleService.indexInProcess(processKey, delegateTask.getName());
            List<String> roleList = testRoleService.listRolesWithCurrentNode(index);
            testRoleService.setUserTaskCandidateUsers(delegateTask, index, roleList);*/
        } else if (EVENTNAME_CREATE.equals(eventName)) {
            // 进入任务
            log.info("task create...");
        } else if (EVENTNAME_COMPLETE.equals(eventName)) {
            // 任务完成
            log.info("task complete...");
        } else {
            // 任务完成之后，要从ACT_RU_TASK中删除这条记录
            log.info("task delete...");
        }
    }
}
