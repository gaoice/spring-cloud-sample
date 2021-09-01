package com.gaoice.cloud.nebula.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.gaoice.cloud"})
public class NebulaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(NebulaAuthApplication.class, args);
    }

}
