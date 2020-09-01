package com.example.eureka;

import com.example.common.access.AccessWarning;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        AccessWarning.disableAccessWarnings();
        SpringApplication.run(EurekaApplication.class, args);
    }

}
