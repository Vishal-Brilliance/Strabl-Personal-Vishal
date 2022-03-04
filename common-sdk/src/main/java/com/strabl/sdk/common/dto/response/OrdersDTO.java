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
public class OrdersDTO {

    private Integer id;
    private UUID uuid;
    private UserResponseDTO user;
    private AddressDTO address;
    private ProductResponse product;
    private CurrencyDTO currency;
    private String paymentType;
    private PaymentDTO payement;
    private String type;
    private Integer quantity;
    private String status;
    private Double ipf;
    private boolean isipf;
    private Instant startDate;
    private Instant endDate;
    private Instant createdAt;
    private Instant updatedAt;
}
