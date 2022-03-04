package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.util.RandomKeyGenerator;
import com.strabl.sdk.domain.entity.*;

import java.util.Locale;

public class ProductMapper {

  private ProductMapper() {}

  public static ProductResponse toProductResponse(Product product) {

    return ProductResponse.builder()
         .id(product.getId())
        .uuid(product.getUuid())
        .customer(UserMapper.toUserResponseDTO(product.getCustomer()))
        .address(AddressMapper.toAddressDTO(product.getAddress()))
        .category(CategoryMapper.toCategoryDTO(product.getCategory()))
        .subCategory(CategoryMapper.toCategoryDTO(product.getSubCategory()))
        .classification(product.getClassification() != null ? ClassificationMapper.toClassificationDTO(product.getClassification()) : null)
        .tbyb(product.getTbyb() != null ? TBYBMapper.toTBYBDTO(product.getTbyb()) : null)
            .seller(UserMapper.toUserResponseDTO(product.getSeller()))
        .slug(product.getSlug())
        .title(product.getTitle())
        .description(product.getDescription())
        .features(product.getFeatures())
        .tags(product.getTags())
        .currency(CurrencyMapper.toCurrencyDTO(product.getCurrency()))
        .finalPrice(product.getFinalPrice())
            .brand(product.getBrand())
        .isActive(product.getIsActive())
        .isApproved(product.getIsApproved())
        .isFeatured(product.getIsFeatured())
        .createdAt(product.getCreatedAt())
        .updatedAt(product.getUpdatedAt())
        .build();
  }

  public static Product toProductEntityFrom(CreateProductRequest createProductRequest, User user) {

    Product product = Product.builder()
        .customer(user)
        .address(Address.builder().id(createProductRequest.getAddressId()).build())
        .category(Category.builder().id(createProductRequest.getCategoryId()).build())
        .subCategory(Category.builder().id(createProductRequest.getSubcategoryId()).build())
            .seller(user)
        .slug(makeSlug(createProductRequest.getTitle()))
        .title(createProductRequest.getTitle())
        .description(createProductRequest.getDescription())
        .features(createProductRequest.getFeatures())
        .tags(createProductRequest.getTags())
        .currency(Currency.builder().id(createProductRequest.getCurrencyId()).build())
        .finalPrice(createProductRequest.getFinalPrice())
            .brand(createProductRequest.getBrand())
        .isActive(false)
        .isApproved(false)
        .isFeatured(false)
        .build();

    if (createProductRequest.getOfferingType() == OfferingType.RENTAL) {
      product.setClassification(ClassificationMapper.toClassificationEntityFrom(createProductRequest.getClassification()));
    } else {
      product.setTbyb(TBYBMapper.toTBYBEntityFrom(createProductRequest.getTbyb()));
    }

    return product;
  }

  private static String makeSlug(String title) {
    return title
        .replaceAll("\\s+", "-")
        .toLowerCase(Locale.ROOT)
        + "-"
        + RandomKeyGenerator.generateRandomString(12);
  }
}

