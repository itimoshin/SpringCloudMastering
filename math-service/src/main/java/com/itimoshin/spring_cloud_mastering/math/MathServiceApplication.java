package com.itimoshin.spring_cloud_mastering.math;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MathServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MathServiceApplication.class, args);
    }
}
