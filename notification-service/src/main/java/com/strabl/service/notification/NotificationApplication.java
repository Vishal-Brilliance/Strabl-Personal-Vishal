package com.strabl.service.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
@ComponentScan(basePackages = {"com.strabl"})
@EntityScan("com.strabl.sdk.domain.entity")
@EnableJpaRepositories("com.strabl.sdk.domain.repository")
@EnableRedisRepositories(basePackages = {"com.strabl.sdk.domain.redis.repository"})
public class NotificationApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationApplication.class, args);
  }
}
