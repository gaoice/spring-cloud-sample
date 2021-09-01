package com.gaoice.cloud.nebula.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.gaoice.cloud"})
public class NSUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(NSUserApplication.class, args);
    }

}
