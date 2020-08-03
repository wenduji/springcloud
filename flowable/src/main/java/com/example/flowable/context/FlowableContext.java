package com.example.flowable.context;

import com.example.flowable.config.DatabaseSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.stereotype.Component;

/**
 * @author hjs
 * @date 2020/8/3
 * @description
 */
@Component
@Slf4j
public class FlowableContext {
    private static ProcessEngine processEngine = null;

    private FlowableContext() {
    }

    public synchronized static ProcessEngine initProcessEngine(final DatabaseSourceProperties databaseSourceProperties) {
        if (processEngine == null) {
            synchronized (FlowableContext.class) {
                if (processEngine == null) {
                    ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                            .setJdbcUrl(databaseSourceProperties.getUrl())
                            .setJdbcUsername(databaseSourceProperties.getUsername())
                            .setJdbcPassword(databaseSourceProperties.getPassword())
                            .setJdbcDriver(databaseSourceProperties.getDriverClassName())
                            .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
                    processEngine = cfg.buildProcessEngine();
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
}
