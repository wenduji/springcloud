package com.example.activiti.business.listener;

import com.example.activiti.business.service.UserTaskInfoService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hjs
 * @date 2020/8/13
 * @description 处理组任务
 */
@Service("groupListener")
@Slf4j
public class GroupListener implements TaskListener {

    private static final long serialVersionUID = 3751958445937091039L;

    @Resource
    private UserTaskInfoService userTaskInfoService;

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();
        if (EVENTNAME_ASSIGNMENT.equals(eventName)) {
            // 任务配置处理人，比create先执行
            log.info("task assignment...");
        } else if (EVENTNAME_CREATE.equals(eventName)) {
            // 进入任务
            log.info("task create...");
            String nodeId = delegateTask.getTaskDefinitionKey();
            String processKey = delegateTask.getProcessDefinitionId().split(":")[0];
            // 组任务设置任务参与人
//            userTaskInfoService.listCandidateUsers(processKey, nodeId);
            // nodeId跟数据库的taskId匹配
            if ("_5".equals(nodeId)) {
                List<String> candidateUsers = new ArrayList<>();
                candidateUsers.add("role A");
                candidateUsers.add("role B");
                delegateTask.addCandidateUsers(candidateUsers);
                log.info("add candidateUsers...");
            }
        } else if (EVENTNAME_COMPLETE.equals(eventName)) {
            // 任务完成
            log.info("task complete...");
        } else {
            // 任务完成之后，要从ACT_RU_TASK中删除这条记录
            log.info("task delete...");
        }
    }
}
