package com.strabl.sdk.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class ProductReviewRequest extends BaseRequest {

  @Min(1)
  @Max(5)
  @NotNull
  private Short rating;

  @NotBlank
  @Size(min = 1, max = 512)
  private String review;
}
