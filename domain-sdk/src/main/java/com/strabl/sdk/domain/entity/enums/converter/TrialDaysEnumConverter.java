package com.strabl.sdk.domain.entity.enums.converter;

import com.strabl.sdk.domain.entity.enums.columns.TrialDaysEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class TrialDaysEnumConverter implements AttributeConverter<TrialDaysEnum, String> {

  @Override
  public String convertToDatabaseColumn(TrialDaysEnum trialDaysEnum) {
    return Optional.of(trialDaysEnum.getEntityCode()).orElse(null);
  }

  @Override
  public TrialDaysEnum convertToEntityAttribute(String entityCode) {
    return TrialDaysEnum.from(entityCode);
  }
}
