package com.strabl.service.seller.service;

import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import org.springframework.data.domain.Page;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;

public interface SellerService {

	String becomeSeller(Integer id);

    void changeStatus(Integer id, UserStatus inactive);

    Page<ProductResponse> sellerProduct(Integer sellerId , PagedResponseRequest pagedResponseRequest);
}
