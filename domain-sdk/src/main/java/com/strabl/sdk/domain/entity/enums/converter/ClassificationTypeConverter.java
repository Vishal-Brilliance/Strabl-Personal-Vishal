package com.strabl.sdk.domain.entity.enums.converter;

import com.strabl.sdk.domain.entity.enums.columns.ClassificationType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class ClassificationTypeConverter implements AttributeConverter<ClassificationType, String> {

  @Override
  public String convertToDatabaseColumn(ClassificationType classificationType) {
    return Optional.of(classificationType.getEntityCode()).orElse(null);
  }

  @Override
  public ClassificationType convertToEntityAttribute(String entityCode) {
    return ClassificationType.from(entityCode);
  }
}
