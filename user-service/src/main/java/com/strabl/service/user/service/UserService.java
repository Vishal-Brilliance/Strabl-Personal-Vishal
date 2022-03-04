package com.strabl.service.user.service;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.ForgotPasswordRequest;
import com.strabl.sdk.common.dto.request.LoginRequest;
import com.strabl.sdk.common.dto.request.RegisterRequest;
import com.strabl.sdk.common.dto.request.ResetPasswordRequest;
import com.strabl.sdk.common.dto.response.LoginResponse;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import org.springframework.data.domain.Page;

public interface UserService {

  UserResponseDTO register(RegisterRequest registerRequest);

  void verify(String token);

  LoginResponse login(LoginRequest loginRequest);

  void logout(String sessionToken);

  void forgotPassword(ForgotPasswordRequest forgotPasswordRequest);

  void resetPassword(ResetPasswordRequest resetPasswordRequest, String forgotPasswordToken);

  LoginResponse refreshToken(String refreshToken);

  void deleteUserById(Integer id);

  void updateStatus(Integer id, UserStatus inactive);

  Page<ProductResponse> getSellerItems(Integer productId, PagedResponseRequest pagedResponseRequest);
}
