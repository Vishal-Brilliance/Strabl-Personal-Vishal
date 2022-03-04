package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.CurrencyDTO;
import com.strabl.sdk.domain.entity.Currency;

public class CurrencyMapper {
  public static CurrencyDTO toCurrencyDTO(Currency currency) {
    return CurrencyDTO.builder()
            .id(currency.getId())
            .uuid(currency.getUuid())
            .currencyIso(currency.getCurrencyIso())
            .currencyName(currency.getCurrencyName())
            .currencySymbol(currency.getCurrencySymbol())
            .createdAt(currency.getCreatedAt())
            .updatedAt(currency.getUpdatedAt())
            .build();
  }
}
