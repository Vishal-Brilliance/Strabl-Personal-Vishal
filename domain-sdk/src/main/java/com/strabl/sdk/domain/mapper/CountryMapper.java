package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.CountryDTO;
import com.strabl.sdk.domain.entity.Country;

public class CountryMapper {

    public static CountryDTO toCountryDTO(Country country){
        return CountryDTO.builder()
                .id(country.getId())
                .country_uuid(country.getCountry_uuid())
                .countryFlagUrl(country.getCountryFlagUrl())
                .countryName(country.getCountryName())
                .countryIsoName(country.getCountryIsoName())
                .status(country.getStatus())
                .createdAt(country.getCreatedAt())
                .updatedAt(country.getUpdatedAt())
                .build();
    }

    public static Country toCountryEntityFrom(CountryDTO countryDTO){
        return Country.builder()
                .country_uuid(countryDTO.getCountry_uuid())
                .countryFlagUrl(countryDTO.getCountryFlagUrl())
                .countryIsoName(countryDTO.getCountryIsoName())
                .countryName(countryDTO.getCountryName())
                .status(false)
                .createdAt(countryDTO.getCreatedAt())
                .updatedAt(countryDTO.getUpdatedAt())
                .build();

    }
}
