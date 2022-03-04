package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.CartDTO;
import com.strabl.sdk.domain.entity.Cart;

import java.util.stream.Collectors;

public class CartMapper {

    public static CartDTO toCartDto(Cart cart){
        return CartDTO.builder()
                .id(cart.getId())
                .productResponseList(cart.getProductList().stream().map(ProductMapper::toProductResponse).collect(Collectors.toSet()))
                .build();
    }
}
