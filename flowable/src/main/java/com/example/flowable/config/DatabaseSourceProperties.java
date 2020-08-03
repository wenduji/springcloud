package com.example.flowable.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hjs
 * @date 2020/8/3
 * @description
 */
@Getter
@Setter
@Component("databaseSourceProperties")
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseSourceProperties {
    private String url;

    private String username;

    private String password;

    private String driverClassName;
}
