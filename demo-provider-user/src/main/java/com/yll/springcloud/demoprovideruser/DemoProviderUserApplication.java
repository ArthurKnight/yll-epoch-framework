package com.yll.springcloud.demoprovideruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoProviderUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProviderUserApplication.class, args);
    }
}
