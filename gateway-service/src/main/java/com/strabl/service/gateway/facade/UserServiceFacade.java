package com.strabl.service.gateway.facade;

import com.strabl.sdk.common.dto.request.ForgotPasswordRequest;
import com.strabl.sdk.common.dto.request.LoginRequest;
import com.strabl.sdk.common.dto.request.RegisterRequest;
import com.strabl.sdk.common.dto.request.ResetPasswordRequest;
import com.strabl.sdk.common.dto.response.LoginResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;

public interface UserServiceFacade {

  ResponseDTO<UserResponseDTO> register(RegisterRequest registerRequest);

  ResponseDTO<UserResponseDTO> verify(String verificationToken);

  ResponseDTO<LoginResponse> login(LoginRequest request);

  ResponseDTO<LoginResponse> refreshToken(String refreshToken);

  ResponseDTO<Void> logout(String sessionToken);

  ResponseDTO<Void> forgotPassword(ForgotPasswordRequest forgotPasswordRequest);

  ResponseDTO<Void> resetPassword(ResetPasswordRequest resetPasswordRequest, String forgotPasswordToken);
}
