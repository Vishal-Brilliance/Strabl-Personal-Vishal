package com.strabl.service.gateway.facade.impl;

import com.strabl.sdk.common.dto.request.ForgotPasswordRequest;
import com.strabl.sdk.common.dto.request.LoginRequest;
import com.strabl.sdk.common.dto.request.RegisterRequest;
import com.strabl.sdk.common.dto.request.ResetPasswordRequest;
import com.strabl.sdk.common.dto.response.AuthDTO;
import com.strabl.sdk.common.dto.response.LoginResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.common.exception.StrablGatewayException;
import com.strabl.service.gateway.client.UserServiceClient;
import com.strabl.service.gateway.facade.UserServiceFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static com.strabl.sdk.common.error.ResponseType.BAD_CREDENTIALS;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceFacadeImpl implements UserServiceFacade {

    private final UserServiceClient userServiceClient;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseDTO<UserResponseDTO> register(RegisterRequest registerRequest) {

        return userServiceClient.signUp(registerRequest);
    }

    @Override
    public ResponseDTO<UserResponseDTO> verify(String token) {

        return userServiceClient.verify(token);
    }

    @Override
    public ResponseDTO<LoginResponse> login(LoginRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (AuthenticationException ex) {
            throw StrablGatewayException.of(BAD_CREDENTIALS);
        }

        LoginResponse loginResponse = LoginResponse.builder()
            .profile((UserResponseDTO) authentication.getPrincipal())
            .auth((AuthDTO) authentication.getCredentials())
            .build();

        return ResponseDTO.success(loginResponse);
    }

  @Override
  public ResponseDTO<LoginResponse> refreshToken(String refreshToken) {

      return userServiceClient.refreshToken(refreshToken);
  }

  @Override
  public ResponseDTO<Void> logout(String sessionToken) {

      return userServiceClient.logout(sessionToken);
  }

  @Override
    public ResponseDTO<Void> forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {

        return userServiceClient.forgotPassword(forgotPasswordRequest);
    }

    @Override
    public ResponseDTO<Void> resetPassword(ResetPasswordRequest resetPasswordRequest, String forgotPasswordToken) {

        return userServiceClient.resetPassword(resetPasswordRequest, forgotPasswordToken);
    }
}
