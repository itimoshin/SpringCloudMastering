package com.itimoshin.spring_cloud_mastering.theology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TheologyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheologyServiceApplication.class, args);
    }
}
