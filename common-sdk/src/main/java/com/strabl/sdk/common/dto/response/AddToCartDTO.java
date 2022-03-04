package com.strabl.sdk.common.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddToCartDTO {

    private Integer id;

    @NotNull
    private  Integer product_id;

    @NotNull
    private  Integer quantity;

    @Override
    public String toString() {
        return "CartDTO{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ",";

    }

}
