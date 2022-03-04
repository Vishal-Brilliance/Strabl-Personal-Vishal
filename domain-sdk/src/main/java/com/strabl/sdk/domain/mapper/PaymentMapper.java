package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.PaymentDTO;
import com.strabl.sdk.domain.entity.Payment;
import com.strabl.sdk.domain.entity.User;


public class PaymentMapper{

    public static PaymentDTO toPaymentDTO(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .payment_uuid(payment.getPayment_uuid())
                .paymentName(payment.getPaymentName())
                .status(payment.isStatus())
                .user(UserMapper.toUserResponseDTO(payment.getUser()))
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }
    public static Payment toPaymentEntityFrom(PaymentDTO paymentDTO, User user) {
        return Payment.builder()
                .payment_uuid(paymentDTO.getPayment_uuid())
                .paymentName(paymentDTO.getPaymentName())
                .status(paymentDTO.isStatus())
                .user(user)
                .build();

    }
}
