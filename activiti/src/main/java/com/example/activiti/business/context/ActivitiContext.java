package com.example.activiti.business.context;

import com.example.activiti.business.entity.CheckBoxFormType;
import com.example.activiti.business.listener.ProcessEngineLifeCycleListener;
import org.activiti.engine.*;
import org.activiti.engine.form.AbstractFormType;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

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
                    List<AbstractFormType> customFormTypes = Arrays.asList(new CheckBoxFormType());
                    springProcessEngineConfiguration
                            // 自定义Form变量类型
                            .setCustomFormTypes(customFormTypes)
                            // 设置流程引擎生命监听器
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
