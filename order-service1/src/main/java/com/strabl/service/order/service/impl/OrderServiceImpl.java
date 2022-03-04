package com.strabl.service.order.service.impl;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.OrdersDTO;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.Orders;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.entity.enums.columns.OrdersStatus;
import com.strabl.sdk.domain.mapper.OrdersMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.strabl.sdk.domain.dao.OrderDao;
import com.strabl.service.order.service.OrderService;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderDao orderDao;
	private final UserDao userDao;


	@Override
	public Page<OrdersDTO> getOrderByCustomerId( Integer user_id, PagedResponseRequest pagedResponseRequest) {
		return orderDao.getOrderByCustomerId(user_id,pagedResponseRequest)
				.map(OrdersMapper::toOrdersDTO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OrdersDTO placeOrder(OrdersDTO ordersDTO, Integer userId) {
		return orderDao.makeOrder(ordersDTO, userId);
	}

}
