package com.cap10mycap10.ouathservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
public class OuathServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OuathServiceApplication.class, args);
    }

}
