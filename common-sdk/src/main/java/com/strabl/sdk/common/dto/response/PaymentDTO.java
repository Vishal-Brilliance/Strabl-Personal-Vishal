package com.strabl.sdk.common.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO implements Serializable {

    private Integer id;
    private UUID payment_uuid;
    private String paymentName;
    private boolean status;
    private UserResponseDTO user;
    private Instant createdAt;
    private Instant updatedAt;
}
