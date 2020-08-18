package com.dtinone.datashare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.cdjiamigu.datasource.feign.interfaces")
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude = MongoAutoConfiguration.class)
public class RunApp {

    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
    }

}
