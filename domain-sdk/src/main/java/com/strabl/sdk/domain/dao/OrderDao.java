package com.strabl.sdk.domain.dao;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.domain.entity.Orders;
import com.strabl.sdk.domain.entity.enums.columns.OrdersStatus;
import org.springframework.data.domain.Page;

public interface OrderDao {

	Page<Orders> getOrderByCustomerId(Integer user_id, PagedResponseRequest pagedResponseRequest);

    Orders makeOrder(Orders orders);

}
