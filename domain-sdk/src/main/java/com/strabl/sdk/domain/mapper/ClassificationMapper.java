package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.ClassificationDTO;
import com.strabl.sdk.domain.entity.Classification;
import com.strabl.sdk.domain.entity.enums.columns.ClassificationType;

public class ClassificationMapper {
  public static ClassificationDTO toClassificationDTO(Classification classification) {

    return ClassificationDTO.builder()
        .uuid(classification.getUuid())
        .classificationType(classification.getClassificationType().getEntityCode())
        .minimumDays(classification.getMinimumDays())
        .rentPerDay(classification.getRentPerDay())
        .rentPerWeek(classification.getRentPerWeek())
        .rentPerMonth(classification.getRentPerMonth())
        .minimumMonths(classification.getMinimumMonths())
        .rentLongTerm(classification.getRentLongTerm())
        .isFeatured(classification.getIsFeatured())
        .build();
  }

  public static Classification toClassificationEntityFrom(ClassificationDTO classificationDTO) {
    return Classification.builder()
        .classificationType(ClassificationType.from(classificationDTO.getClassificationType()))
        .minimumDays(classificationDTO.getMinimumDays())
        .rentPerDay(classificationDTO.getRentPerDay())
        .rentPerWeek(classificationDTO.getRentPerWeek())
        .rentPerMonth(classificationDTO.getRentPerMonth())
        .minimumMonths(classificationDTO.getMinimumMonths())
        .rentLongTerm(classificationDTO.getRentLongTerm())
        .isFeatured(false)
        .build();
  }
}
