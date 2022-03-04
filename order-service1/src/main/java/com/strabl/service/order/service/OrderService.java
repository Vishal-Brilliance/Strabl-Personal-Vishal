package com.strabl.service.order.service;


import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.OrdersDTO;
import com.strabl.sdk.domain.entity.Orders;
import com.strabl.sdk.domain.entity.enums.columns.OrdersStatus;
import org.springframework.data.domain.Page;

public interface OrderService {

	Page<OrdersDTO> getOrderByCustomerId(Integer user_id , PagedResponseRequest pagedResponseRequest);

    OrdersDTO placeOrder(Orders orders, Integer userId);

}
