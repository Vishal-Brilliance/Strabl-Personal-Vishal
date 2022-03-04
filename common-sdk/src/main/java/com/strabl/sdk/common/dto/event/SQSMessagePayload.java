package com.strabl.sdk.common.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SQSMessagePayload {

  @JsonProperty("Type")
  private String type;

  @JsonProperty("MessageId")
  private String messageId;

  @JsonProperty("TopicArn")
  private String topicArn;

  @JsonProperty("Subject")
  private String subject;

  @JsonProperty("Message")
  private String message;

  @JsonProperty("MessageAttributes")
  private Map<String, EventConsumerPayload> messageAttributes;
}
