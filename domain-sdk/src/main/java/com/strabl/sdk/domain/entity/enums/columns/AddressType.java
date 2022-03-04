package com.strabl.sdk.domain.entity.enums.columns;

import com.strabl.sdk.domain.entity.enums.EntityCodeEnum;
import com.strabl.sdk.domain.entity.enums.EntityCodeEnumMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AddressType implements EntityCodeEnum {

  HOME("H"),
  OFFICE("O"),
  CUSTOM("C");

  // Changing entityCode values (H, O, C) in code will break the enum mapping to/from DB column
  // REF: AddressTypeConverter.java
  private String entityCode;

  public static AddressType from(String entityCode) {
    return EntityCodeEnumMapper.fromEntityCode(AddressType.class, entityCode);
  }
}
