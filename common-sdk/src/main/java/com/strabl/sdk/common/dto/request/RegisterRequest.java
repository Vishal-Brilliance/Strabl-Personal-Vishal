package com.strabl.sdk.common.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest extends BaseRequest {

  @NotBlank
   private String fullName;

  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  private String phone_number;

  private String profilePicture;
}
