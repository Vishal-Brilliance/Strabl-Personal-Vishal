package com.strabl.sdk.common.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

  private Integer id;
  private UUID uuid;
  private UserResponseDTO customer;
  private AddressDTO address;
  private CategoryDTO category;
  private CategoryDTO subCategory;
  private ClassificationDTO classification;
  private TBYBDTO tbyb;
  private UserResponseDTO seller;
  private String slug;
  private String title;
  private String description;
  private String features;
  private String tags;
  private CurrencyDTO currency;
  private Long finalPrice;
  private Boolean isActive;
  private Boolean isApproved;
  private Boolean isFeatured;
  private Instant createdAt;
  private Instant updatedAt;
  private String brand;
}
