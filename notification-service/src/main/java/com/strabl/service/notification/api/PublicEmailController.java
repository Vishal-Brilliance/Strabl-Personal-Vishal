package com.strabl.service.notification.api;

import com.strabl.sdk.common.dto.event.EventType;
import com.strabl.sdk.common.dto.request.SendEmailRequest;
import com.strabl.service.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1/public/api/email")
@RequiredArgsConstructor
public class PublicEmailController {

  private final EmailService emailService;

  @PostMapping("send")
  public void sendEmail(
      @RequestParam EventType eventType, @RequestBody SendEmailRequest sendEmailRequest) {
    emailService.sendEmail(eventType, sendEmailRequest);
  }
}
