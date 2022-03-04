package com.strabl.service.user.client;

import com.strabl.sdk.common.dto.event.EventType;
import com.strabl.sdk.common.dto.request.SendEmailRequest;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notification-service")
public interface NotificationServiceClient {

  @PostMapping("v1/public/api/email/send")
  ResponseDTO sendEmail(@RequestParam EventType eventType, @RequestBody SendEmailRequest sendEmailRequest);

}


