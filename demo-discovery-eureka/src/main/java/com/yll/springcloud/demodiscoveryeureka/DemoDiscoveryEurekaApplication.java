package com.yll.springcloud.demodiscoveryeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DemoDiscoveryEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDiscoveryEurekaApplication.class, args);
    }
}
