package com.example.study;

import com.example.common.access.AccessWarning;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.example.**.mapper")
@EnableDiscoveryClient
public class StudyApplication {

    public static void main(String[] args) {
        AccessWarning.disableAccessWarnings();
        SpringApplication.run(StudyApplication.class, args);
    }

}
