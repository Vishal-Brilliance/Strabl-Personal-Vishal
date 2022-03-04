package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.TransactionDTO;
import com.strabl.sdk.common.util.RandomKeyGenerator;
import com.strabl.sdk.domain.entity.Currency;
import com.strabl.sdk.domain.entity.Orders;
import com.strabl.sdk.domain.entity.Transaction;

public class TransactionMapper {

    public static TransactionDTO toTransactionResponse(Transaction transaction) {

        return TransactionDTO.builder()
                .id(transaction.getId())
                .uuid(transaction.getUuid())
                .currency(CurrencyMapper.toCurrencyDTO(transaction.getCurrency()))
                .orders(OrdersMapper.toOrdersDTO(transaction.getOrders()))
                .isTbyb(transaction.getIsTbyb())
                .invoiceId(makeInvoice(transaction.getInvoiceId()))
                .initialAmount(transaction.getInitialAmount())
                .remainingAmount(transaction.getRemainingAmount())
                .commission(transaction.getCommission())
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .build();

    }

    public static Transaction toTransactionEntityForm(TransactionDTO transactionDTO){
        return Transaction.builder()
                .orders(Orders.builder().id(transactionDTO.getId()).build())
                .currency(Currency.builder().id(transactionDTO.getId()).build())
                .isTbyb(false)
                .invoiceId(makeInvoice(transactionDTO.getInvoiceId()))
                .initialAmount(transactionDTO.getInitialAmount())
                .remainingAmount(transactionDTO.getRemainingAmount())
                .commission(transactionDTO.getCommission())
                .build();

    }

    private static String makeInvoice(String id){
        return id
                + RandomKeyGenerator.generateRandomString(6);
    }
}
