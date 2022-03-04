package com.strabl.sdk.domain.entity.enums.converter;

import com.strabl.sdk.domain.entity.enums.columns.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {

  @Override
  public String convertToDatabaseColumn(UserStatus userStatus) {
    return Optional.of(userStatus.getEntityCode()).orElse(null);
  }

  @Override
  public UserStatus convertToEntityAttribute(String entityCode) {
    return UserStatus.from(entityCode);
  }
}
