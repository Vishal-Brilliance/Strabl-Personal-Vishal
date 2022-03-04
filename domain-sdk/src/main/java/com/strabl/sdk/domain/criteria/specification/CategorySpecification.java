package com.strabl.sdk.domain.criteria.specification;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.entity.ProductReview;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {

  private CategorySpecification() {
  }

  public static Specification<Category> of(OfferingType offeringType) {

    return (Category, cq, cb) -> {
      switch (offeringType) {
        case TBYB:
          return cb.and(
              cb.isTrue(Category.get("isTbyb")),
              cb.isTrue(Category.get("isPublished"))
          );

        case RENTAL:
          return cb.and(
              cb.isTrue(Category.get("isRental")),
              cb.isTrue(Category.get("isPublished"))
          );

        default:
          return cb.disjunction(); // Matches nothing
      }
    };
  }

  public static Specification<Category> getByFilterCategory(boolean isRental, boolean isTbyb) {
    return (root, cq, cb) -> {
      return cb.and(
              cb.equal(root.get("isRental"), isRental),
              cb.equal(root.get("isTbyb"), isTbyb)
      );
    };
  }
}

