package com.strabl.sdk.common.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ForgotPasswordRequest extends BaseRequest {

  @NotBlank
  private String email;
}
