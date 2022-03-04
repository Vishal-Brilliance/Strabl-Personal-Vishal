package com.strabl.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class, LiquibaseAutoConfiguration.class})
@ComponentScan(basePackages = {"com.strabl"})
@EntityScan("com.strabl.sdk.domain.entity")
@EnableScheduling
@EnableJpaRepositories("com.strabl.sdk.domain.repository")
@EnableRedisRepositories(basePackages = {"com.strabl.sdk.domain.redis.repository"})
public class UserApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }


}
