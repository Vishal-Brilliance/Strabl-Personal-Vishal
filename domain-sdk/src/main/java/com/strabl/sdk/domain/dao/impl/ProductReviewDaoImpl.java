package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.criteria.page.StrablPageable;
import com.strabl.sdk.domain.criteria.specification.ProductReviewSpecification;
import com.strabl.sdk.domain.dao.ProductReviewDao;
import com.strabl.sdk.domain.entity.ProductReview;
import com.strabl.sdk.domain.repository.ProductReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@AllArgsConstructor
public class ProductReviewDaoImpl implements ProductReviewDao {

  private final ProductReviewRepository productReviewRepository;

  private final EntityManager entityManager;

  @Override
  public ProductReview create(ProductReview productReview) {

    productReviewRepository.save(productReview);
    entityManager.refresh(productReview);
    return productReview;
  }

  @Override
  public Page<ProductReview> findBy(Integer productId, PagedResponseRequest pagedResponseRequest) {

    Specification<ProductReview> specification = ProductReviewSpecification.by(productId);
    Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);

    return productReviewRepository.findAll(specification, pageable);
  }

  @Override
  public ProductReview updateApprovalStatus(Integer reviewId, boolean approvalStatus) {

    ProductReview productReview = productReviewRepository
        .findById(reviewId)
        .orElseThrow(() -> StrablException.of(ResponseType.PRODUCT_REVIEW_NOT_FOUND));

    productReview.setIsApproved(approvalStatus);

    return productReviewRepository.save(productReview);
  }

  @Override
  public Page<ProductReview> getUnapprovedReviews(PagedResponseRequest pagedResponseRequest) {

    Specification<ProductReview> specification = ProductReviewSpecification.unapproved();
    Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);

    return productReviewRepository.findAll(specification, pageable);
  }

}
