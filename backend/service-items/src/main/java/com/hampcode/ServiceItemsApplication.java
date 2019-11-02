package com.hampcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RibbonClient("service-products")
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class ServiceItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceItemsApplication.class, args);
	}

}
