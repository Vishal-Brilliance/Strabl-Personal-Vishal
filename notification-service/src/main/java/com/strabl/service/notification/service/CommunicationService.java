package com.strabl.service.notification.service;

import com.strabl.sdk.common.dto.request.SendEmailRequest;

public interface CommunicationService {

  void sendEmail(String eventName, SendEmailRequest sendEmailRequest);
}
