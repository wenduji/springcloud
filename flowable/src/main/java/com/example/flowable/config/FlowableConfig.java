package com.example.flowable.config;

import com.example.flowable.context.FlowableContext;
import org.flowable.engine.ProcessEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hjs
 * @date 2020/8/3
 * @description
 */
@Configuration
public class FlowableConfig {

    @Bean
    DatabaseSourceProperties getDatabaseSourceProperties() {
        return new DatabaseSourceProperties();
    }

    // 初始化工作流程引擎
    @Bean(name = "flowableProcessEngine")
    public ProcessEngine initProcessEngine(DatabaseSourceProperties databaseSourceProperties) {
        ProcessEngine processEngine = FlowableContext.initProcessEngine(databaseSourceProperties);
        return processEngine;
    }
}
