package com.strabl.service.user.event.publisher;

import com.strabl.sdk.common.dto.event.EventRequest;

public interface EventPublisher {

  void publish(EventRequest payload);
}
