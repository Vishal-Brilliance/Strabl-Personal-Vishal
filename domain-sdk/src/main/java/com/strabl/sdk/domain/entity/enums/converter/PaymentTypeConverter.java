package com.strabl.sdk.domain.entity.enums.converter;

import com.strabl.sdk.domain.entity.enums.columns.PaymentType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class PaymentTypeConverter implements AttributeConverter<PaymentType, String> {
    @Override
    public String convertToDatabaseColumn(PaymentType paymentType) {
        return Optional.of(paymentType.getEntityCode()).orElse(null);
    }

    @Override
    public PaymentType convertToEntityAttribute(String entityCode) {
        return PaymentType.from(entityCode);
    }
}
