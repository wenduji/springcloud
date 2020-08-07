package com.example.activiti;

import com.example.common.access.AccessWarning;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivitiApplication {

    public static void main(String[] args) {
        AccessWarning.disableAccessWarnings();
        SpringApplication.run(ActivitiApplication.class, args);
    }

}
