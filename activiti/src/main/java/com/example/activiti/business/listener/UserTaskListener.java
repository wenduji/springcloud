package com.example.activiti.business.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

/**
 * @author hjs
 * @date 2020/8/13
 * @description
 */
@Service("userTaskListener")
@Slf4j
public class UserTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();
        if (EVENTNAME_ASSIGNMENT.equals(eventName)) {
            // 任务配置处理人，比create先执行
            log.info("task assignment...");
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
