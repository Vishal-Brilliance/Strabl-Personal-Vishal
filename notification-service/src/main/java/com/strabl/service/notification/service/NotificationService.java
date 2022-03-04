package com.strabl.service.notification.service;

import com.strabl.sdk.common.dto.request.SendEmailRequest;
import com.strabl.sdk.domain.entity.Notification;

public interface NotificationService {

  void sendEmail(SendEmailRequest communication, Notification notification);
}
