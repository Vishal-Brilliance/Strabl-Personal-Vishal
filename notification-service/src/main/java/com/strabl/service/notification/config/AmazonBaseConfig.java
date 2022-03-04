package com.strabl.service.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonBaseConfig {

  @Value("${cloud.aws.region.static}")
  protected String region;

  @Value("${cloud.aws.credentials.access-key}")
  protected String awsAccessKey;

  @Value("${cloud.aws.credentials.secret-key}")
  protected String awsSecretKey;
}
