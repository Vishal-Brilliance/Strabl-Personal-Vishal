package com.strabl.sdk.common.dto.request;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.response.ClassificationDTO;
import com.strabl.sdk.common.dto.response.TBYBDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class CreateProductRequest extends BaseRequest {

  @NotNull
  @Positive
  private Integer addressId;

  @NotNull
  @Positive
  private Integer categoryId;

  @NotNull
  @Positive
  private Integer subcategoryId;

  @NotNull
  @Positive
  private Integer currencyId;

  @NotNull
  private OfferingType offeringType;

  private ClassificationDTO classification;
  private TBYBDTO tbyb;

  @NotNull
  private List<Integer> tagsList;

  @Size(min = 1, max = 200)
  @NotNull
  @NotBlank
  private String title;

  @Size(min = 1, max = 512)
  @NotNull
  @NotBlank
  private String description;

  @Size(min = 1, max = 255)
  @NotNull
  @NotBlank
  private String features;

  @Size(min = 1, max = 255)
  @NotNull
  @NotBlank
  private String tags;

  @NotNull
  @PositiveOrZero
  private Long finalPrice;

  @Size(min = 1, max = 255)
  @NotNull
  @NotBlank
  private String brand;

}
