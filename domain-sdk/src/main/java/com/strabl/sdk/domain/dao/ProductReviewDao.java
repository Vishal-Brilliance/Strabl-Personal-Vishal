package com.strabl.sdk.domain.dao;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.domain.entity.ProductReview;

import org.springframework.data.domain.Page;

public interface ProductReviewDao {

  ProductReview create(ProductReview productReview);

  Page<ProductReview> findBy(Integer productId, PagedResponseRequest pagedResponseRequest);

  ProductReview updateApprovalStatus(Integer reviewId, boolean approvalStatus);

  Page<ProductReview> getUnapprovedReviews(PagedResponseRequest pagedResponseRequest);
}
