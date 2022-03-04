package com.strabl.service.notification.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonSESConfig extends AmazonBaseConfig {

  @Bean("client")
  public AmazonSimpleEmailService initClient() {
    return AmazonSimpleEmailServiceClientBuilder.standard()
        .withRegion(Regions.EU_WEST_1)
        .withCredentials(
            new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
        .build();
  }
}
