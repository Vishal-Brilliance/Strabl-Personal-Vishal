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
public class TransactionDTO {

    private Integer id;
    private UUID uuid;
    private OrdersDTO orders;
    private CurrencyDTO currency;
    private String invoiceId;
    private Boolean isTbyb;
    private Double initialAmount;
    private Double remainingAmount;
    private Double commission;
    private Instant createdAt;
    private Instant updatedAt;
}
