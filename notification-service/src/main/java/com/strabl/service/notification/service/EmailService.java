package com.strabl.service.notification.service;

import com.strabl.sdk.common.dto.event.EventType;
import com.strabl.sdk.common.dto.request.SendEmailRequest;

public interface EmailService {

  void sendEmail(EventType eventType, SendEmailRequest sendEmailRequest);
}
