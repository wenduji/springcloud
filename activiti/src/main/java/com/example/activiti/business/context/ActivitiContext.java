package com.example.activiti.business.context;

import com.example.activiti.business.listener.ProcessEngineLifeCycleListener;
import org.activiti.engine.*;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.stereotype.Component;

/**
 * @author hjs
 * @date 2020/3/13
 * @description 提供工作流引擎相关service
 */
@Component
public class ActivitiContext {
    private ActivitiContext() {
    }

    private static ProcessEngine processEngine = null;

    public synchronized static ProcessEngine initProcessEngine(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        if (processEngine == null) {
            synchronized (ActivitiContext.class) {
                if (processEngine == null) {
                    springProcessEngineConfiguration
                            .setProcessEngineLifecycleListener(new ProcessEngineLifeCycleListener());
                    processEngine = springProcessEngineConfiguration.buildProcessEngine();
                }
            }
        }
        return processEngine;
    }

    public static RuntimeService getRuntimeService() {
        return processEngine.getRuntimeService();
    }

    public static IdentityService getIdentityService() {
        return processEngine.getIdentityService();
    }

    public static TaskService getTaskService() {
        return processEngine.getTaskService();
    }

    public static HistoryService getHistoryService() {
        return processEngine.getHistoryService();
    }

    public static RepositoryService getRepositoryService() {
        return processEngine.getRepositoryService();
    }

    public static FormService getFormService() {
        return processEngine.getFormService();
    }
}
