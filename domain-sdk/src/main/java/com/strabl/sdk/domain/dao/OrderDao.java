package com.strabl.sdk.domain.dao;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.OrdersDTO;
import com.strabl.sdk.domain.entity.Orders;
import com.strabl.sdk.domain.entity.enums.columns.OrdersStatus;
import org.springframework.data.domain.Page;

public interface OrderDao {

	Page<Orders> getOrderByCustomerId(Integer user_id, PagedResponseRequest pagedResponseRequest);

    OrdersDTO makeOrder(OrdersDTO ordersDTO, Integer userId);

    Page<OrdersDTO> getCompleteOrders(Integer userId, PagedResponseRequest pagedResponseRequest);

    Page<OrdersDTO> getPendingOrders(Integer userId, PagedResponseRequest pagedResponseRequest);

    Page<OrdersDTO> getCancelledOrders(Integer userId, PagedResponseRequest pagedResponseRequest);

}
