package com.strabl.service.product.service.impl;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.ProductReviewRequest;
import com.strabl.sdk.common.dto.response.ProductReviewResponse;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.dao.ProductDao;
import com.strabl.sdk.domain.dao.ProductReviewDao;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.entity.ProductReview;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.mapper.ProductReviewMapper;
import com.strabl.service.product.service.ReviewService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final UserDao userDao;
  private final ProductDao productDao;
  private final ProductReviewDao productReviewDao;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ProductReviewResponse addReview(String productSlug, Integer userId, ProductReviewRequest productReviewRequest) {

    User user = userDao.findByIdOrThrow(userId);

    if (!user.getIsEnabled()) {
      throw StrablException.of(ResponseType.USER_NOT_VERIFIED_OR_ENABLED);
    }

    Product product = productDao.findBySlug(productSlug);

    ProductReview productReview = ProductReviewMapper.toProductReviewEntity(productReviewRequest, product.getId(), userId);
    ProductReview savedReview = productReviewDao.create(productReview);

    return ProductReviewMapper.toProductReviewResponse(savedReview);
  }

  @Override
  public Page<ProductReviewResponse> getReviewsBy(String productSlug, PagedResponseRequest pagedResponseRequest) {

    Product product = productDao.findBySlug(productSlug);

    return productReviewDao
        .findBy(product.getId(), pagedResponseRequest)
        .map(ProductReviewMapper::toProductReviewResponse);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ProductReviewResponse updateApprovalStatus(Integer reviewId, boolean approvalStatus) {

    return ProductReviewMapper.toProductReviewResponse(productReviewDao.updateApprovalStatus(reviewId, approvalStatus));
  }

  @Override
  public Page<ProductReviewResponse> getUnapprovedReviews(PagedResponseRequest pagedResponseRequest) {

    return productReviewDao.getUnapprovedReviews(pagedResponseRequest)
        .map(ProductReviewMapper::toProductReviewResponse);
  }


}
