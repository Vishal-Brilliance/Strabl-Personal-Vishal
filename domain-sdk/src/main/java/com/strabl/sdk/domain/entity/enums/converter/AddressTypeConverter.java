package com.strabl.sdk.domain.entity.enums.converter;

import com.strabl.sdk.domain.entity.enums.columns.AddressType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class AddressTypeConverter implements AttributeConverter<AddressType, String> {

  @Override
  public String convertToDatabaseColumn(AddressType addressType) {
    return Optional.of(addressType.getEntityCode()).orElse(null);
  }

  @Override
  public AddressType convertToEntityAttribute(String entityCode) {
    return AddressType.from(entityCode);
  }
}
