package com.example.activiti.business.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

/**
 * @author hjs
 * @date 2020/8/13
 * @description 监听流程实例
 */
@Service("beExecutionListener")
@Slf4j
public class BeExecutionListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        String eventName = execution.getEventName();
        if (EVENTNAME_START.equals(eventName)) {
            log.info("start...");
        } else if (EVENTNAME_END.equals(eventName)) {
            log.info("end...");
        } else {
            log.info("take...");
        }
    }
}
