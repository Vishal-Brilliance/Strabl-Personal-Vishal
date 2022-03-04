package com.strabl.sdk.common.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

  @NotNull
  @NotBlank
  private String name;

  @NotNull
  @NotBlank
  private String description;

  private Integer orderIndex;
  private String image;
  private String icon;
  private Boolean isPublished;
  private Boolean isHome;
  private Boolean isRandomStuff;

  @NotNull
  private Boolean isTbyb;

  @NotNull
  private Boolean isRental;

  private String categoryUrl;
  private String url;

  @NotNull
  private Integer createdBy;

  private String createdByName;

  private Integer updatedBy;
  private String updatedByName;
}
