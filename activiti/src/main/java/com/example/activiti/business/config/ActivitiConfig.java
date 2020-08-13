package com.example.activiti.business.config;

import com.example.activiti.business.context.ActivitiContext;
import com.example.activiti.business.listener.ProcessEngineLifeCycleListener;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author hjs
 * @date 2020/3/13
 * @description
 */
@Configuration
public class ActivitiConfig {

    @Resource
    private PlatformTransactionManager transactionManager;

    @Resource
    private DataSource dataSource;

    @Bean
    DataSourceProperties getDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource) {
        SpringProcessEngineConfiguration springProcessEngineConfiguration =
                new SpringProcessEngineConfiguration();
        springProcessEngineConfiguration.setDataSource(dataSource);
        springProcessEngineConfiguration
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        springProcessEngineConfiguration.setTransactionManager(transactionManager);
        springProcessEngineConfiguration
                .setProcessEngineLifecycleListener(new ProcessEngineLifeCycleListener());
        return springProcessEngineConfiguration;
    }

    //初始化工作流程引擎
    @Bean(name="activitiProcessEngine")
    public ProcessEngine initProcessEngine() {
        return ActivitiContext.initProcessEngine(processEngineConfiguration(dataSource));
    }
}
