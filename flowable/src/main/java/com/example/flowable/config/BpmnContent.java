package com.example.flowable.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @author hjs
 * @date 2020/8/3
 * @description
 */
@PropertySource(value = {"classpath:/bpmn_content.properties"}, encoding = "utf-8")
@Configuration
@Getter
public class BpmnContent {

    @Value("#{'${definitions_keys}'.split(',')}")
    private List<String> definitionsKeys;

    @Value("#{'${definitions_values}'.split(',')}")
    private List<String> definitionsValues;
}
