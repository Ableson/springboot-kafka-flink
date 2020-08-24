package com.dtinone.datashare;

import com.cdjiamigu.common.system.SystemMonitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.cdjiamigu.datasource.feign.interfaces")
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude = MongoAutoConfiguration.class)
@EnableScheduling
public class RunApp {

    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
        SystemMonitor.startMonitor("sharePlatform", 5, false);
    }

}
