package com.strabl.service.gateway.client;

import com.strabl.sdk.common.constants.StrablHeaders;
import com.strabl.sdk.common.dto.request.ForgotPasswordRequest;
import com.strabl.sdk.common.dto.request.LoginRequest;
import com.strabl.sdk.common.dto.request.RegisterRequest;
import com.strabl.sdk.common.dto.request.ResetPasswordRequest;
import com.strabl.sdk.common.dto.response.LoginResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface UserServiceClient {

  @PostMapping("v1/api/user/register")
  ResponseDTO<UserResponseDTO> signUp(@RequestBody RegisterRequest registerRequest);

  @GetMapping("v1/api/user/verify")
  ResponseDTO<UserResponseDTO> verify(@RequestParam String verificationToken);

  @PostMapping("v1/api/user/login")
  ResponseDTO<LoginResponse> login(@RequestBody LoginRequest loginRequest);

  @GetMapping("v1/api/user/refresh-token")
  ResponseDTO<LoginResponse> refreshToken(@RequestHeader(StrablHeaders.REFRESH_TOKEN) String refreshToken);

  @GetMapping("v1/api/user/logout")
  ResponseDTO<Void> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String sessionToken);

  @PostMapping("v1/api/user/forgot-password")
  ResponseDTO<Void> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest);

  @PostMapping("v1/api/user/reset-password")
  ResponseDTO<Void> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest, @RequestParam String forgotPasswordToken);
}


