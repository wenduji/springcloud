package com.example.activiti;

import com.example.common.access.AccessWarning;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.servlet.annotation.WebFilter;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, org.activiti.spring.boot.SecurityAutoConfiguration.class})
@WebFilter("/service/*")
public class ActivitiApplication {

    public static void main(String[] args) {
        AccessWarning.disableAccessWarnings();
        SpringApplication.run(ActivitiApplication.class, args);
    }

}
