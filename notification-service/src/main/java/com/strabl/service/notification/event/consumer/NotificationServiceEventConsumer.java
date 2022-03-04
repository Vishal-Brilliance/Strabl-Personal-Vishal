package com.strabl.service.notification.event.consumer;

import com.strabl.sdk.common.dto.event.EventRequest;
import com.strabl.sdk.common.dto.event.SQSMessagePayload;
import com.strabl.sdk.common.dto.request.SendEmailRequest;
import com.strabl.sdk.common.util.JsonUtils;
import com.strabl.service.notification.service.CommunicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
@RequiredArgsConstructor
public class NotificationServiceEventConsumer {

  private final CommunicationService communicationService;

//  @SqsListener("${service.event.queue}")
  public void loadMessageFromSQS(String message) {

    log.info("message from SQS Queue {}", message);

    SQSMessagePayload sqsMessagePayload = JsonUtils.toObject(message, SQSMessagePayload.class);

    log.info("After parsing message from SQS Queue {}", sqsMessagePayload);

    EventRequest eventRequest =
        JsonUtils.toObject(sqsMessagePayload.getMessage(), EventRequest.class);

    switch (eventRequest.getEventType()) {
      case SEND_SIGNUP_EMAIL:
      case WELCOME_EMAIL:
      case FORGOT_PASSWORD:
        communicationService.sendEmail(
            eventRequest.getEventType().name(),
            JsonUtils.toObject(eventRequest.getData(), SendEmailRequest.class));
        break;
      default:
        log.info("Need to invoke consumer for event {}", eventRequest.getEventType());
    }
  }
}
