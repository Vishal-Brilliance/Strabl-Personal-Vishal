package com.strabl.service.seller.service;

import com.strabl.sdk.common.dto.response.OrdersDTO;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import org.springframework.data.domain.Page;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;

public interface SellerService {

	String becomeSeller(Integer id);

    void changeStatus(Integer id, UserStatus inactive);

    Page<ProductResponse> sellerProduct(Integer sellerId , PagedResponseRequest pagedResponseRequest);

    User sellerInfo(Integer id);

    Page<OrdersDTO> getCompleteOrders(Integer userId, PagedResponseRequest pagedResponseRequest);

    Page<OrdersDTO> getPendingOrders(Integer userId, PagedResponseRequest pagedResponseRequest);

    Page<OrdersDTO> getCancelledOrders(Integer userId, PagedResponseRequest pagedResponseRequest);

}
