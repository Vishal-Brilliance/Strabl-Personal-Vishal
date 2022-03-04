package com.strabl.service.gateway.api.anonymous;

import com.strabl.sdk.common.constants.StrablHeaders;
import com.strabl.sdk.common.dto.request.ForgotPasswordRequest;
import com.strabl.sdk.common.dto.request.LoginRequest;
import com.strabl.sdk.common.dto.request.RegisterRequest;
import com.strabl.sdk.common.dto.request.ResetPasswordRequest;
import com.strabl.sdk.common.dto.response.LoginResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.service.gateway.api.BaseController;
import com.strabl.service.gateway.facade.UserServiceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("v1/public/api/user")
@AllArgsConstructor
@Api(
    tags = {"Public User Controller"},
    value =
        "This controller is responsible for all user operations where an access token is not required")
public class PublicUserController extends BaseController {

  private final UserServiceFacade userServiceFacade;

  @ApiOperation(value = "Registers a user on the Strabl platform")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "User registered successfully and you'll receive an email on your provided address to verify it.",
          response = UserResponseDTO.class),
      @ApiResponse(
          code = 400,
          message = "Invalid request body was provided for registration",
          response = ResponseDTO.class)
  })
  @PostMapping("register")
  public ResponseEntity<ResponseDTO<UserResponseDTO>> register(
      @Valid @RequestBody RegisterRequest registerRequest) {

    return toResponseEntity(userServiceFacade.register(registerRequest));
  }

  @ApiOperation(value = "Verifies the email of a newly registered user")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "User email verified successfully",
          response = UserResponseDTO.class),
      @ApiResponse(
          code = 400,
          message = "User email could not be verified",
          response = ResponseDTO.class)
  })
  @GetMapping("verify")
  public ResponseEntity<ResponseDTO<UserResponseDTO>> verify(
      @RequestParam("verificationToken") String verificationToken) {

    return toResponseEntity(userServiceFacade.verify(verificationToken));
  }

  @ApiOperation(value = "Logs in the user by authenticating the user and returns the user profile")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "User logged in successfully",
          response = LoginResponse.class),
      @ApiResponse(
          code = 400,
          message = "Invalid request body provided for logging in",
          response = ResponseDTO.class)
  })
  @PostMapping("login")
  public ResponseEntity<ResponseDTO<LoginResponse>> login(
      @Valid @RequestBody LoginRequest loginRequest) {

    return toResponseEntity(userServiceFacade.login(loginRequest));
  }

  @ApiOperation(value = "Refreshes the user session by generating a new pair of Session and Refresh tokens. The refresh token used for this call will be invalidated.")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Session created successfully",
          response = LoginResponse.class),
      @ApiResponse(
          code = 400,
          message = "Invalid request headers provided for refreshing the session",
          response = ResponseDTO.class)
  })
  @GetMapping("refresh-token")
  public ResponseEntity<ResponseDTO<LoginResponse>> refreshToken(
      @RequestHeader(StrablHeaders.REFRESH_TOKEN) String refreshToken
  ) {

    return toResponseEntity(userServiceFacade.refreshToken(refreshToken));
  }

  @ApiOperation(value = "Sends an email to the user to reset their password")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Forgot Password email sent to the user successfully",
          response = ResponseDTO.class),
      @ApiResponse(
          code = 400,
          message = "Strabl was unable to send the email to the user",
          response = ResponseDTO.class)
  })
  @PostMapping("forgot-password")
  public ResponseEntity<ResponseDTO<Void>> forgotPassword(
      @Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {

    return toResponseEntity(userServiceFacade.forgotPassword(forgotPasswordRequest));
  }

  @ApiOperation(value = "Resets the password the user after verifying the forgot password token")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "User's password updated successfully",
          response = ResponseDTO.class),
      @ApiResponse(
          code = 400,
          message = "Strabl was unable to update the User's password",
          response = ResponseDTO.class)
  })
  @PostMapping("reset-password")
  public ResponseEntity<ResponseDTO<Void>> resetPassword(
      @Valid @RequestBody ResetPasswordRequest resetPasswordRequest,
      @RequestParam String forgotPasswordToken) {

    return toResponseEntity(userServiceFacade.resetPassword(resetPasswordRequest, forgotPasswordToken));
  }
}
