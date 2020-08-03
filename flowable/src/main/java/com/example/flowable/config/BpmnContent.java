package com.example.flowable.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author hjs
 * @date 2020/8/3
 * @description
 */
@PropertySource(value = {"classpath:/bpmn_content.properties"}, encoding = "utf-8")
@Configuration
@Getter
public class BpmnContent {
    @Value("${xml_head}")
    private String xmlHead;

    @Value("${definitions_head}")
    private String definitionsHead;

    @Value("${definitions_tail}")
    private String definitionsTail;
}
