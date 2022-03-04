package com.strabl.sdk.domain.criteria.specification;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.domain.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

  private ProductSpecification() {}

  public static Specification<Product> bySlug(String slug) {

    return (product, cq, cb) -> cb.and(
        cb.equal(product.get("slug"), slug),
        cb.isTrue(product.get("isActive")),
        cb.isTrue(product.get("isApproved"))
    );
  }

  public static Specification<Product> byTagAndTitle(String keyword) {

    return (product, cq, cb) -> cb.and(
        cb.or(
            cb.like(product.get("tags"), "%" + keyword + "%"),
            cb.like(product.get("title"), "%" + keyword + "%")
        ),
        cb.isTrue(product.get("isActive")),
        cb.isTrue(product.get("isApproved"))
    );
  }

  public static Specification<Product> byCategory(String category) {

    return (product, cq, cb) -> cb.and(
        cb.equal(product.get("category").get("name"), category)
//        cb.isTrue(product.get("isActive")),
//        cb.isTrue(product.get("isApproved"))

   );
  }

  public static Specification<Product> byOfferingType(OfferingType offeringType) {

    return (product, cq, cb) -> {
      switch (offeringType) {
        case TBYB:
          return cb.and(
              cb.isNotNull(product.get("tbyb")),
              cb.isTrue(product.get("isActive")),
              cb.isTrue(product.get("isApproved"))
          );

        case RENTAL:
          return cb.and(
              cb.isNotNull(product.get("classification")),
              cb.isTrue(product.get("isActive")),
              cb.isTrue(product.get("isApproved"))
          );

        default:
          return cb.disjunction(); // Matches nothing
      }
    };
  }

  public static Specification<Product> pendingApproval() {

    return (product, cq, cb) -> cb.isFalse(product.get("isApproved"));
  }

  public static Specification<Product> byBrandTypeSubCategory(String brand, OfferingType offeringType, String subCategory) {

    return (product, cq, cb) -> {
      switch (offeringType) {
        case TBYB:
          return cb.and(
                  cb.isNotNull(product.get("tbyb")),
                  cb.equal(product.get("subCategory").get("name"), subCategory),
                  cb.like(cb.lower(product.get("brand")), "%" + brand.toLowerCase() + "%")
          );

        case RENTAL:
          return cb.and(
                  cb.isNotNull(product.get("classification")),
                  cb.equal(product.get("subCategory").get("name"), subCategory),
                  cb.like(cb.lower(product.get("brand")), "%" + brand.toLowerCase() + "%")
          );

        default:
          return cb.disjunction(); // Matches nothing
      }
    };
  }

    public static Specification<Product> bySubCategory(Integer subcategory_id) {
      return (product, cq, cb) -> cb.and(
              cb.equal(product.get("subCategory"),subcategory_id)

      );
    }

  public static Specification<Product> sellerItems(Integer sellerId) {
    return (product ,cq ,cb) -> cb.and(
           cb.equal(product.get("seller"),sellerId)
    );
  }
}

