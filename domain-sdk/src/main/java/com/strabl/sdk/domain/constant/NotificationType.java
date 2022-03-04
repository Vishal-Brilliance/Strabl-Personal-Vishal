package com.strabl.sdk.domain.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum NotificationType {
  EMAIL(1),
  SMS(2),
  PUSH_NOTIFICATION(3);

  private static final Map<Integer, NotificationType> KEY_BY_ID_MAP = new HashMap<>();

  static {
    Stream.of(NotificationType.values())
        .forEach(discountType -> KEY_BY_ID_MAP.put(discountType.getId(), discountType));
  }

  private Integer id;

  NotificationType(Integer id) {
    this.id = id;
  }

  public static NotificationType getById(int id) {
    return KEY_BY_ID_MAP.get(id);
  }

  public Integer getId() {
    return this.id;
  }
}
