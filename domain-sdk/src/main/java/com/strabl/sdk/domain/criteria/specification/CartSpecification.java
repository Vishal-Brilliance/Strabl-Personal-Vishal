package com.strabl.sdk.domain.criteria.specification;

import com.strabl.sdk.domain.entity.Cart;
import org.springframework.data.jpa.domain.Specification;

public class CartSpecification {

    public CartSpecification() {
    }

    public static Specification<Cart> getCartByUserId(Integer userId) {
        return (root, cq, cb) -> {
            return cb.and(
                    cb.equal(root.get("user"), userId)
            );
        };
    }
}
