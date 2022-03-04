package com.strabl.service.notification.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/public/api/notification")
@RequiredArgsConstructor
public class PublicNotificationController {

//  private final CommunicationService communicationService;
//
//  @PostMapping("send/email")
//  public String sendEmail(
//      @RequestParam String eventName, @RequestBody CommunicationDto communicationDto) {
//    communicationService.sendEmail(eventName, communicationDto);
//    return "Done";
//  }
}
