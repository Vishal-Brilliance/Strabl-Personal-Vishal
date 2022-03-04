package com.strabl.service.user.event.builder;

import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;

import java.util.HashMap;

public class PublishRequestBuilder {

  private PublishRequest publishRequest;

  public PublishRequestBuilder() {
    this.publishRequest = new PublishRequest();
  }

  public PublishRequestBuilder setTopicArn(String topicArn) {
    this.publishRequest.setTopicArn(topicArn);
    return this;
  }

  public PublishRequestBuilder setMessage(String message) {
    this.publishRequest.setMessage(message);
    return this;
  }

  public PublishRequestBuilder setSubject(String subject) {
    this.publishRequest.setSubject(subject);
    return this;
  }

  public PublishRequestBuilder setMessageAttribute(String consumerService) {
    MessageAttributeValue messageAttributeValue = new MessageAttributeValue();
    messageAttributeValue.setDataType("String");
    messageAttributeValue.setStringValue(consumerService);
    this.publishRequest.setMessageAttributes(
        new HashMap<>() {
          {
            put("eventConsumer", messageAttributeValue);
          }
        });
    return this;
  }

  public PublishRequest build() {
    return this.publishRequest;
  }
}
