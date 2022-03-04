package com.strabl.sdk.domain.criteria.specification;

import com.strabl.sdk.domain.entity.ProductReview;
import org.springframework.data.jpa.domain.Specification;

public class
ProductReviewSpecification {

  private ProductReviewSpecification() {}

  public static Specification<ProductReview> by(Integer productId) {

    return (productReview, cq, cb) -> cb.and(
        cb.equal(productReview.get("product").get("id"), productId),
        cb.isTrue(productReview.get("isApproved"))
    );
  }

  public static Specification<ProductReview> unapproved() {

    return (productReview, cq, cb) -> cb.isFalse(productReview.get("isApproved"));
  }
}
