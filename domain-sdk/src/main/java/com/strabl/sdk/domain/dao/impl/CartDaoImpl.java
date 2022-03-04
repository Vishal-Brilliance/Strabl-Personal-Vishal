package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.domain.criteria.specification.CartSpecification;
import com.strabl.sdk.domain.dao.CartDao;
import com.strabl.sdk.domain.entity.Cart;
import com.strabl.sdk.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartDaoImpl implements CartDao {

    private final CartRepository cartRepository;

    @Override
    public Cart findByUserId(Integer userId) {
        Specification<Cart> specification = CartSpecification.getCartByUserId(userId);
        return cartRepository.findOne(specification).orElse(null);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
