package com.strabl.sdk.domain.entity.enums.columns;

import com.strabl.sdk.domain.entity.enums.EntityCodeEnum;
import com.strabl.sdk.domain.entity.enums.EntityCodeEnumMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus implements EntityCodeEnum {
  ACTIVE("A"),
  INACTIVE("I"),
  INVITED("V"),
  UNVERIFIED("U");

  // Changing entityCode values (A, I, V, U) in code will break the enum mapping to/from DB column
  // REF: UserStatusConverter.java
  private String entityCode;

  public static UserStatus from(String entityCode) {
    return EntityCodeEnumMapper.fromEntityCode(UserStatus.class, entityCode);
  }
}
