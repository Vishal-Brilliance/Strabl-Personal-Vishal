package com.strabl.sdk.common.dto.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDTO {

    private Integer id;
    private UUID uuid;
    private UserResponseDTO user_id;
    private ProductResponse product_id;
    private Integer quantity;
}