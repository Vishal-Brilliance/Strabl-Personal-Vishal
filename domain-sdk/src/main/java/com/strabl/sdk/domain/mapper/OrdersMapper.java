package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.ClassificationDTO;
import com.strabl.sdk.common.dto.response.OrdersDTO;
import com.strabl.sdk.domain.entity.*;
import com.strabl.sdk.domain.entity.enums.columns.ClassificationType;
import com.strabl.sdk.domain.entity.enums.columns.OrdersStatus;
import com.strabl.sdk.domain.entity.enums.columns.PaymentType;

import java.util.stream.Collectors;


public class OrdersMapper{

    public static OrdersDTO toOrdersDTO(Orders orders) {
        return OrdersDTO.builder()
                .id(orders.getId())
                .uuid(orders.getUuid())
                .address(AddressMapper.toAddressDTO(orders.getAddress()))
                .cartDTOList(orders.getCartList().stream().map(CartMapper::toCartDto).collect(Collectors.toList()))
                .currency(CurrencyMapper.toCurrencyDTO(orders.getCurrency()))
                .user(UserMapper.toUserResponseDTO(orders.getUser()))
                .status(orders.getStatus().getEntityCode())
                .type(orders.getType().getEntityCode())
                .paymentType(orders.getPaymentType().getEntityCode())
                .payement(PaymentMapper.toPaymentDTO(orders.getPayement()))
                .ipf(orders.getIpf())
                .isipf(orders.isIsipf())
                .startDate(orders.getStartDate())
                .endDate(orders.getEndDate())
                .createdAt(orders.getCreatedAt())
                .updatedAt(orders.getUpdatedAt())
                .build();

    }

    public static Orders toOrdersEntityFrom(OrdersDTO ordersDTO, User user) {
        return Orders.builder()
                .type(ClassificationType.from(ordersDTO.getClassification().getClassificationType()))
                .status(OrdersStatus.from(ordersDTO.getStatus()))
                .ipf(ordersDTO.getIpf())
                .address(Address.builder().id(ordersDTO.getAddress().getId()).build())
                .currency(Currency.builder().id(ordersDTO.getId()).build())
                .payement(Payment.builder().id(ordersDTO.getId()).build())
                .isipf(false)
                .endDate(ordersDTO.getEndDate())
                .startDate(ordersDTO.getStartDate())
                .paymentType(PaymentType.from(ordersDTO.getPaymentType()))
                .status(OrdersStatus.from(ordersDTO.getStatus()))
                .user(user)
                .build();
    }
}
