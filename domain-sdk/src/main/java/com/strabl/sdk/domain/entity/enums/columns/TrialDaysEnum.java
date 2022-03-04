package com.strabl.sdk.domain.entity.enums.columns;

import com.strabl.sdk.domain.entity.enums.EntityCodeEnum;
import com.strabl.sdk.domain.entity.enums.EntityCodeEnumMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TrialDaysEnum implements EntityCodeEnum {

  FIVE("5D"),
  SEVEN("7D"),
  FIFTEEN("15D");

  // Changing entityCode values (5D, 7D, 15D) in code will break the enum mapping to/from DB column
  // REF: TrialDaysEnumConverter.java
  private String entityCode;

  public static TrialDaysEnum from(String entityCode) {
    return EntityCodeEnumMapper.fromEntityCode(TrialDaysEnum.class, entityCode);
  }
}
