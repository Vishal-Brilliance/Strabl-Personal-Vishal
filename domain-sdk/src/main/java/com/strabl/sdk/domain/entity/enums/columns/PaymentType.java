package com.strabl.sdk.domain.entity.enums.columns;

import com.strabl.sdk.domain.entity.enums.EntityCodeEnum;
import com.strabl.sdk.domain.entity.enums.EntityCodeEnumMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentType implements EntityCodeEnum {

    CARD("CD"),
    CASH("CH");

    private String entityCode;

    public static PaymentType from(String entityCode) {
        return EntityCodeEnumMapper.fromEntityCode(PaymentType.class, entityCode);
    }
}
