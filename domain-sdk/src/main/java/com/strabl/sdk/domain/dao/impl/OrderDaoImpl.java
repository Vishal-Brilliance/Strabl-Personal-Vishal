package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.OrdersDTO;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.criteria.page.StrablPageable;
import com.strabl.sdk.domain.criteria.specification.OrdersSpecification;
import com.strabl.sdk.domain.dao.CartDao;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.Cart;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.entity.enums.columns.OrdersStatus;
import com.strabl.sdk.domain.mapper.OrdersMapper;
import com.strabl.sdk.domain.repository.ProductRepository;
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
import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderDaoImpl implements OrderDao {

	private final OrderRepository orderRepository;
	private final EntityManager entityManager;
	private final UserDao userDao;
	private final CartDao cartDao;
	private final ProductRepository productRepository;


	@Override
	public Page<Orders> getOrderByCustomerId(Integer user_id, PagedResponseRequest pagedResponseRequest) {
		Specification<Orders> specification = OrdersSpecification.byUserId(user_id);
		Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);

		return orderRepository.findAll(specification, pageable);

	}

	@Override
	public OrdersDTO makeOrder(OrdersDTO ordersDTO, Integer userId) {
		User user = userDao.findByIdOrThrow(userId);
		Cart cart = cartDao.findByUserId(userId);
		try {
			Orders orders = OrdersMapper.toOrdersEntityFrom(ordersDTO, user);
			orders.setCreatedAt(Instant.now());
			orders.getCartList().add(cart);
			cart.getOrdersList().add(orders);
			return OrdersMapper.toOrdersDTO(orderRepository.save(orders));
		} catch (Exception e) {
			throw new StrablException(ResponseType.BAD_REQUEST);
		}
	}

	@Override
	public Page<OrdersDTO> getCompleteOrders(Integer userId, PagedResponseRequest pagedResponseRequest) {
		User user = userDao.findByIdOrThrow(userId);
		if (!user.getIsseller()) {
			throw new StrablException(ResponseType.BAD_REQUEST);
		}
		Specification<Orders> specification = OrdersSpecification.byFilter(userId, OrdersStatus.COMPLETED);
		Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);
		Page<Orders> ordersList = orderRepository.findAll(specification, pageable);
		for (Orders o : ordersList) {
			for (Cart c : new HashSet<>(o.getCartList())) {
				for (Product p : new HashSet<>(c.getProductList())) {
					p.setQuantity(productRepository.getProductQuantityInCart(c.getId(), p.getId()));
				}
			}
		}
		return ordersList.map(OrdersMapper::toOrdersDTO);
	}

	@Override
	public Page<OrdersDTO> getPendingOrders(Integer userId, PagedResponseRequest pagedResponseRequest) {
		User user = userDao.findByIdOrThrow(userId);
		if (!user.getIsseller()) {
			throw new StrablException(ResponseType.BAD_REQUEST);
		}
		Specification<Orders> specification = OrdersSpecification.byFilter(userId, OrdersStatus.PENDING);
		Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);
		Page<Orders> ordersList = orderRepository.findAll(specification, pageable);
		for (Orders o : ordersList) {
			for (Cart c : new HashSet<>(o.getCartList())) {
				for (Product p : new HashSet<>(c.getProductList())) {
					p.setQuantity(productRepository.getProductQuantityInCart(c.getId(), p.getId()));
				}
			}
		}
		return ordersList.map(OrdersMapper::toOrdersDTO);
	}

	@Override
	public Page<OrdersDTO> getCancelledOrders(Integer userId, PagedResponseRequest pagedResponseRequest) {
		User user = userDao.findByIdOrThrow(userId);
		if (!user.getIsseller()) {
			throw new StrablException(ResponseType.BAD_REQUEST);
		}
		Specification<Orders> specification = OrdersSpecification.byFilter(userId, OrdersStatus.CANCELLED);
		Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);
		Page<Orders> ordersList = orderRepository.findAll(specification, pageable);
		for (Orders o : ordersList) {
			for (Cart c : new HashSet<>(o.getCartList())) {
				for (Product p : new HashSet<>(c.getProductList())) {
					p.setQuantity(productRepository.getProductQuantityInCart(c.getId(), p.getId()));
				}
			}
		}
		return ordersList.map(OrdersMapper::toOrdersDTO);
	}
}