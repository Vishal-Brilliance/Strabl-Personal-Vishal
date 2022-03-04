package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.domain.criteria.page.StrablPageable;
import com.strabl.sdk.domain.criteria.specification.OrdersSpecification;
import com.strabl.sdk.domain.entity.enums.columns.OrdersStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.strabl.sdk.domain.dao.OrderDao;
import com.strabl.sdk.domain.entity.Orders;
import com.strabl.sdk.domain.repository.OrderRepository;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderDaoImpl implements OrderDao {

	private final OrderRepository orderRepository;
	private final EntityManager entityManager;


	@Override
	public Page<Orders> getOrderByCustomerId(Integer user_id, PagedResponseRequest pagedResponseRequest) {
		Specification<Orders> specification = OrdersSpecification.byUserId(user_id);
		Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);

		return orderRepository.findAll(specification, pageable);

	}

	@Override
	public Orders makeOrder(Orders orders) {
		orders.setCreatedAt(Instant.now());
		orders.setUpdatedAt(Instant.now());
		Orders savedOrder = orderRepository.save(orders);
		entityManager.refresh(orders);
		return savedOrder;
	}
}