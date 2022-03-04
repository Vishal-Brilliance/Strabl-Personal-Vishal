package com.strabl.service.notification.service.impl;

import com.strabl.sdk.common.dto.request.SendEmailRequest;
import com.strabl.sdk.domain.constant.NotificationType;
import com.strabl.sdk.domain.entity.Notification;
import com.strabl.service.notification.gateway.AmazonSESGateway;
import com.strabl.service.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.strabl.sdk.common.dto.request.EmailTemplateKeys.WEB_PORTAL_HOST;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private final AmazonSESGateway amazonSESGateway;

  @Override
  public void sendEmail(SendEmailRequest communication, Notification notification) {

    log.info("Received communicationDto: {}", communication);

    if (CollectionUtils.isEmpty(communication.getDynamicData())) {
      communication.setDynamicData(new HashMap<>());
    }

    buildMandatoryKeys(communication);

    switch (NotificationType.getById(notification.getNotificationType())) {
      case EMAIL:
        amazonSESGateway.send(
            StringUtils.isEmpty(communication.getFrom()) ? null : communication.getFrom(),
            Arrays.asList(communication.getTo()),
            notification.getTitle(),
            getHtmlContent(notification.getBody(), communication.getDynamicData()));
        break;

      default:
        log.info("Unknown {} notification type", notification.getId());
    }
  }

  private void buildMandatoryKeys(SendEmailRequest communication) {
    communication.getDynamicData().put(WEB_PORTAL_HOST, "");
  }

  private String getHtmlContent(String body, Map<String, String> dynamicData) {

    for (Map.Entry<String, String> entry : dynamicData.entrySet()) {

      body =
          body.replace(
              getKeyPlaceHolder(entry.getKey()),
              Optional.ofNullable(dynamicData.getOrDefault(entry.getKey(), "")).orElse(""));
    }
    return body;
  }

  private String getKeyPlaceHolder(String key) {
    return String.format("${%s}", key);
  }
}
