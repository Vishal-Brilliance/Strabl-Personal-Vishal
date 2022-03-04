package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.entity.User;

public class CategoryMapper {

  public static CategoryDTO toCategoryDTO(Category category) {

    return CategoryDTO.builder()
        .name(category.getName())
        .description(category.getDescription())
        .orderIndex(category.getOrderIndex())
        .image(category.getImage())
        .icon(category.getIcon())
        .isPublished(category.getIsPublished())
        .isHome(category.getIsHome())
        .isRandomStuff(category.getIsRandomStuff())
        .isTbyb(category.getIsTbyb())
        .isRental(category.getIsRental())
        .categoryUrl(category.getCategoryUrl())
        .url(category.getUrl())
        .createdBy(category.getCreatedBy().getId())
        .createdByName(category.getCreatedBy().getFullName())
        .updatedBy(category.getUpdatedBy().getId())
        .updatedByName(category.getUpdatedBy().getFullName())
        .build();
  }

  public static Category toCategoryEntityFrom(CategoryDTO categoryDTO) {

    return Category.builder()
        .name(categoryDTO.getName())
        .description(categoryDTO.getDescription())
        .orderIndex(categoryDTO.getOrderIndex())
        .image(categoryDTO.getImage())
        .icon(categoryDTO.getIcon())
        .isPublished(categoryDTO.getIsPublished() != null ? categoryDTO.getIsPublished() : false)
        .isHome(categoryDTO.getIsHome() != null ? categoryDTO.getIsHome(): false)
        .isRandomStuff(categoryDTO.getIsRandomStuff() != null ? categoryDTO.getIsRandomStuff() : false)
        .isTbyb(categoryDTO.getIsTbyb())
        .isRental(categoryDTO.getIsRental())
        .categoryUrl(categoryDTO.getCategoryUrl())
        .url(categoryDTO.getUrl())
        .createdBy(User.builder().id(categoryDTO.getCreatedBy()).build())
        .updatedBy(User.builder().id(categoryDTO.getUpdatedBy()).build())
        .build();
  }
}
