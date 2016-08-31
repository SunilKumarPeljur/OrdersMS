package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages="com.training.repository")
@EnableDiscoveryClient
@EnableHystrix
public class OrdersMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersMsApplication.class, args);
	}
}
