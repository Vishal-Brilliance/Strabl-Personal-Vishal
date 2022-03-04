package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.request.ProductReviewRequest;
import com.strabl.sdk.common.dto.response.ProductReviewResponse;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.entity.ProductReview;
import com.strabl.sdk.domain.entity.User;

import java.time.Instant;

public class ProductReviewMapper {

  private ProductReviewMapper() {}

  public static ProductReviewResponse toProductReviewResponse(ProductReview productReview) {

    return ProductReviewResponse.builder()
        .uuid(productReview.getUuid())
        .reviewer(UserMapper.toUserResponseDTO(productReview.getReviewer()))
        .review(productReview.getReview())
        .rating(productReview.getRating())
        .build();
  }

  public static ProductReview toProductReviewEntity(ProductReviewRequest productReviewRequest, Integer productId, Integer reviewerUserId) {

    return ProductReview.builder()
        .product(Product.builder().id(productId).build())
        .review(productReviewRequest.getReview())
        .reviewer(User.builder().id(reviewerUserId).build())
        .isApproved(false)
        .rating(productReviewRequest.getRating())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

}
