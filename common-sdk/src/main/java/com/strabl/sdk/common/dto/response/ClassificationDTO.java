package com.strabl.sdk.common.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassificationDTO {

  private UUID uuid;
  private String classificationType;
  private Integer minimumDays;
  private Long rentPerDay;
  private Long rentPerWeek;
  private Long rentPerMonth;
  private Integer minimumMonths;
  private Long rentLongTerm;
  private Boolean isFeatured;
}
