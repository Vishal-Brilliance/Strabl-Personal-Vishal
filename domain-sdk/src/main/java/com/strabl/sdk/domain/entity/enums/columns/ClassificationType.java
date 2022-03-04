package com.strabl.sdk.domain.entity.enums.columns;

import com.strabl.sdk.domain.entity.enums.EntityCodeEnum;
import com.strabl.sdk.domain.entity.enums.EntityCodeEnumMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClassificationType implements EntityCodeEnum {

  SHORT("S"),
  LONG("L"),
  TBYB("T");

  // Changing entityCode values (S, L, T) in code will break the enum mapping to/from DB column
  // REF: ClassificationTypeConverter.java
  private String entityCode;

  public static ClassificationType from(String entityCode) {
    return EntityCodeEnumMapper.fromEntityCode(ClassificationType.class, entityCode);
  }
}
