package com.strabl.service.notification.service.impl;

import com.strabl.sdk.common.dto.event.EventType;
import com.strabl.sdk.common.dto.request.SendEmailRequest;
import com.strabl.sdk.domain.entity.StrablTemplate;
import com.strabl.service.notification.gateway.AmazonSESGateway;
import com.strabl.service.notification.service.EmailService;
import com.strabl.service.notification.service.EmailTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final EmailTemplateService emailTemplateService;
  private final AmazonSESGateway amazonSESGateway;

  private static final String DEFAULT_SENDER_EMAIL = "support@strabl.com";

  @Override
  public void sendEmail(EventType eventType, SendEmailRequest sendEmailRequest) {

    StrablTemplate strablTemplate = emailTemplateService.getEmailTemplateByEventType(eventType);

    if (CollectionUtils.isEmpty(sendEmailRequest.getDynamicData())) {
      sendEmailRequest.setDynamicData(new HashMap<>());
    }

    amazonSESGateway.send(
        StringUtils.isEmpty(sendEmailRequest.getFrom()) ? DEFAULT_SENDER_EMAIL : sendEmailRequest.getFrom(),
        List.of(sendEmailRequest.getTo()),
        strablTemplate.getTitle(),
        getHtmlContent(strablTemplate.getTemplate(), sendEmailRequest.getDynamicData())
    );
  }

  private String getHtmlContent(String body, Map<String, String> dynamicData) {

    for (Map.Entry<String, String> entry : dynamicData.entrySet()) {

      body = body.replace(
              getKeyPlaceHolder(entry.getKey()),
              Optional.ofNullable(entry.getValue()).orElse(""));
    }

    return body;
  }

  private String getKeyPlaceHolder(String key) {
    return String.format("${%s}", key);
  }
}
