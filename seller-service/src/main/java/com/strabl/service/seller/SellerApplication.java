package com.strabl.service.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableDiscoveryClient
@SpringBootApplication(exclude = {LiquibaseAutoConfiguration.class})
@ComponentScan(basePackages = {"com.strabl"})
@EntityScan("com.strabl.sdk.domain.entity")
@EnableJpaRepositories("com.strabl.sdk.domain.repository")
@EnableRedisRepositories(basePackages = {"com.strabl.sdk.domain.redis.repository"})
public class SellerApplication {

	 public static void main(String[] args) {
		    SpringApplication.run(SellerApplication.class, args);
		  }
}
