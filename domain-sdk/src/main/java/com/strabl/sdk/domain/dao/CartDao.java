package com.strabl.sdk.domain.dao;

import com.strabl.sdk.domain.entity.Cart;

public interface CartDao {
    Cart save(Cart cart);

    Cart findByUserId(Integer userId);
}
