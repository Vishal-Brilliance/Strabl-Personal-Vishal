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
public class CurrencyDTO {

    private UUID uuid;
    private Integer id;
    private Instant createdAt;
    private Instant updatedAt;
    private String currencyIso;
    private String currencyName;
    private String currencySymbol;
}
