package com.strabl.sdk.common.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDTO {

    private Integer id;
    private UUID country_uuid;
    private String countryName;
    private String countryFlagUrl;
    private String countryIsoName;
    private Boolean status;
    private Instant createdAt;
    private Instant updatedAt;
}
