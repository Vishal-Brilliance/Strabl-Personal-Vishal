package com.strabl.sdk.domain.entity.enums.columns;

import com.strabl.sdk.domain.entity.enums.EntityCodeEnum;
import com.strabl.sdk.domain.entity.enums.EntityCodeEnumMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrdersStatus implements EntityCodeEnum {

    PENDING("P"),
    ACCEPTED("A"),
    COMPLETED("C"),
    CANCELLED("CD"),
    DECLINED("D");


    private String entityCode;

    public static OrdersStatus from(String entityCode) {
        return EntityCodeEnumMapper.fromEntityCode(OrdersStatus.class, entityCode);
    }
}
