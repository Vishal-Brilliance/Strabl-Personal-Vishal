package com.strabl.service.product.service;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.ProductReviewRequest;
import com.strabl.sdk.common.dto.response.ProductReviewResponse;


import org.springframework.data.domain.Page;

public interface ReviewService {

  ProductReviewResponse addReview(String productSlug, Integer userId, ProductReviewRequest productReviewRequest);

  Page<ProductReviewResponse> getReviewsBy(String productSlug, PagedResponseRequest pagedResponseRequest);

  ProductReviewResponse updateApprovalStatus(Integer reviewId, boolean approvalStatus);

  Page<ProductReviewResponse> getUnapprovedReviews(PagedResponseRequest pagedResponseRequest);

}
