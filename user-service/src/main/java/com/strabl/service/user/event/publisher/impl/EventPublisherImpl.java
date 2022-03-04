package com.strabl.service.user.event.publisher.impl;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.strabl.sdk.common.dto.event.EventRequest;
import com.strabl.sdk.common.util.JsonUtils;
import com.strabl.service.user.event.builder.PublishRequestBuilder;
import com.strabl.service.user.event.publisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventPublisherImpl implements EventPublisher {

  private final AmazonSNSClient amazonSNSClient;

//  @Value("${cloud.aws.end-point.uri}")
//  private String endpoint;

  @Value("arn:aws:sns:eu-west-1:998038686369:MyTopic")
  private String topicArn;

  @Override
  public void publish(EventRequest payload) {

    PublishRequest publishRequest =
        new PublishRequestBuilder()
            .setMessage(JsonUtils.toJson(payload))
            .setTopicArn(topicArn)
            // .setSubject("Sending sign up email to customer")
            .setMessageAttribute(payload.getEventConsumer().name())
            .build();

    amazonSNSClient.publish(publishRequest);
  }
}
