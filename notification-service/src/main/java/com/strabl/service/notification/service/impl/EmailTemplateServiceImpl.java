package com.strabl.service.notification.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strabl.sdk.common.dto.event.EventType;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.entity.StrablTemplate;
import com.strabl.service.notification.service.EmailTemplateService;
import com.strabl.service.notification.util.ResourceUtil;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailTemplateServiceImpl implements EmailTemplateService {

  public static final String EMAIL_TEMPLATES_BASE_PATH = "classpath:templates/email/";

  private final ObjectMapper objectMapper;
  private final ResourceLoader resourceLoader;

  @Override
  public StrablTemplate getEmailTemplateByEventType(EventType eventType) {

    Resource resource = resourceLoader.getResource(getEmailTemplateResource(eventType));
    String s = ResourceUtil.asString(resource);
    try {
      return objectMapper.readValue(s, StrablTemplate.class);
    } catch (JsonProcessingException e) {
      throw StrablException.of(ResponseType.NO_EMAIL_TEMPLATE_FOUND);
    }
  }

  private static String getEmailTemplateResource(EventType eventType) {
    return EMAIL_TEMPLATES_BASE_PATH + eventType.name() + ".json";
  }
}
