package com.strabl.sdk.domain.entity.enums.converter;

import com.strabl.sdk.domain.entity.enums.columns.OrdersStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrdersStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrdersStatus status) {
        return  Optional.of(status.getEntityCode()).orElse(null);
    }

    @Override
    public OrdersStatus convertToEntityAttribute(String entityCode) {
        return OrdersStatus.from(entityCode);
    }
}
