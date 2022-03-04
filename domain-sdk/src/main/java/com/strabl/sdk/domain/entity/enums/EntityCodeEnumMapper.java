package com.strabl.sdk.domain.entity.enums;

import java.util.Arrays;

public abstract class EntityCodeEnumMapper {

  private EntityCodeEnumMapper() {}

  public static <T extends EntityCodeEnum> T fromEntityCode(Class<T> enumType, String entityCode) {
    return Arrays.stream(enumType.getEnumConstants())
        .filter(t -> t.getEntityCode().equals(entityCode))
        .findFirst()
        .orElse(null);
  }
}
