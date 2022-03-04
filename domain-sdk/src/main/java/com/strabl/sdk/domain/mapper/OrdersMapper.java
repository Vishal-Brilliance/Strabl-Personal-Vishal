package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.ClassificationDTO;
import com.strabl.sdk.common.dto.response.OrdersDTO;
import com.strabl.sdk.domain.entity.*;
import com.strabl.sdk.domain.entity.enums.columns.ClassificationType;
import com.strabl.sdk.domain.entity.enums.columns.OrdersStatus;
import com.strabl.sdk.domain.entity.enums.columns.PaymentType;


public class OrdersMapper{

    public static OrdersDTO toOrdersDTO(Orders orders) {
        return OrdersDTO.builder()
                .id(orders.getId())
                .uuid(orders.getUuid())
                .address(AddressMapper.toAddressDTO(orders.getAddress()))
                .product(ProductMapper.toProductResponse(orders.getProduct()))
                .currency(CurrencyMapper.toCurrencyDTO(orders.getCurrency()))
                .user(UserMapper.toUserResponseDTO(orders.getUser()))
                .status(orders.getStatus().getEntityCode())
                .type(orders.getType().getEntityCode())
                .paymentType(orders.getPaymentType().getEntityCode())
                //.payement(PaymentMapper.toPaymentDTO(orders.getPayement()))
                .payement(orders.getPayement() != null ? PaymentMapper.toPaymentDTO(orders.getPayement()) : null)
                .quantity(orders.getQuantity())
                .ipf(orders.getIpf())
                .isipf(orders.isIsipf())
                .startDate(orders.getStartDate())
                .endDate(orders.getEndDate())
                .createdAt(orders.getCreatedAt())
                .updatedAt(orders.getUpdatedAt())
                .build();

    }

    public static Orders toOrdersEntityFrom(OrdersDTO ordersDTO , ClassificationDTO classificationDTO , User user ) {
        return Orders.builder()
                .type(ClassificationType.from(classificationDTO.getClassificationType()))
                .status(OrdersStatus.from(ordersDTO.getStatus()))
                .ipf(ordersDTO.getIpf())
                .address(Address.builder().id(ordersDTO.getAddress().getId()).build())
                .product(Product.builder().id(ordersDTO.getId()).build())
                .currency(Currency.builder().id(ordersDTO.getId()).build())
                .payement(Payment.builder().id(ordersDTO.getId()).build())
                .isipf(false)
                .endDate(ordersDTO.getEndDate())
                .startDate(ordersDTO.getStartDate())
                .paymentType(PaymentType.from(ordersDTO.getPaymentType()))
                .quantity(ordersDTO.getQuantity())
                .status(OrdersStatus.from(ordersDTO.getStatus()))
                .user(user)
                .build();
    }
}
