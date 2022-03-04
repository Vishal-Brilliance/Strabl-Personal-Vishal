package com.strabl.sdk.domain.criteria.specification;

import com.strabl.sdk.domain.entity.Orders;
import org.springframework.data.jpa.domain.Specification;

public class OrdersSpecification {

    private OrdersSpecification() {
    }

    public static Specification<Orders> byUserId(Integer userId) {

        return (Orders, cq, cb) -> cb.and(
                cb.equal(Orders.get("user").get("id"), userId)
        );

    }
}
