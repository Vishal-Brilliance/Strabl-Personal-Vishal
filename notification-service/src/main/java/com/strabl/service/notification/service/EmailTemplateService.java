package com.strabl.service.notification.service;

import com.strabl.sdk.common.dto.event.EventType;
import com.strabl.sdk.domain.entity.StrablTemplate;

public interface EmailTemplateService {

  StrablTemplate getEmailTemplateByEventType(EventType key);
}
