package com.strabl.sdk.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginRequest extends BaseRequest {

  @NotBlank
  private String email;

  @NotBlank
  private String password;
}
