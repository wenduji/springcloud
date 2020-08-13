package com.example.activiti.business.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineLifecycleListener;

/**
 * @author hjs
 * @date 2020/8/13
 * @description 监听流程引擎实例
 */
@Slf4j
public class ProcessEngineLifeCycleListener implements ProcessEngineLifecycleListener {
    @Override
    public void onProcessEngineBuilt(ProcessEngine processEngine) {
        log.info("process engine start...");
    }

    @Override
    public void onProcessEngineClosed(ProcessEngine processEngine) {
        log.info("process engine end...");
    }
}
