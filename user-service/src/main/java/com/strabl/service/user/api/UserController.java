package com.strabl.service.user.api;

import com.strabl.sdk.common.constants.StrablHeaders;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.ForgotPasswordRequest;
import com.strabl.sdk.common.dto.request.LoginRequest;
import com.strabl.sdk.common.dto.request.RegisterRequest;
import com.strabl.sdk.common.dto.request.ResetPasswordRequest;
import com.strabl.sdk.common.dto.response.LoginResponse;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.util.AuthTokenUtil;
import com.strabl.sdk.domain.dao.StrablSessionDao;
import com.strabl.sdk.domain.entity.User;
import com.strabl.service.user.service.SessionService;
import com.strabl.service.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.strabl.sdk.domain.entity.enums.columns.UserStatus.INACTIVE;

@Slf4j
@RestController
@RequestMapping("v1/api/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final StrablSessionDao strablSessionDao;

  @PostMapping("register")
  public ResponseDTO<UserResponseDTO> register(
      @Valid @RequestBody RegisterRequest registerRequest
  ) {

    return ResponseDTO.success(userService.register(registerRequest));
  }

  @GetMapping("verify")
  public ResponseDTO<Void> verify(
      @RequestParam String token
  ) {

    userService.verify(token);
    return ResponseDTO.of(ResponseType.USER_ACCOUNT_VERIFIED);
  }

  @PostMapping("login")
  public ResponseDTO<LoginResponse> login(
          @Valid @RequestBody LoginRequest loginRequest
  ) {

    return ResponseDTO.success(userService.login(loginRequest));
  }

  @GetMapping("refresh-token")
  public ResponseDTO<LoginResponse> refreshToken(
      @RequestHeader(StrablHeaders.REFRESH_TOKEN) String refreshToken
  ) {

    return ResponseDTO.success(userService.refreshToken(refreshToken));
  }

  @GetMapping("logout")
  public ResponseDTO<Void> logout(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String sessionToken
  ) {

    String token = AuthTokenUtil.parseBearerToken(sessionToken);
    userService.logout(token);

    return ResponseDTO.of(ResponseType.LOGOUT);
  }

  @PostMapping("forgot-password")
  public ResponseDTO<Void> forgotPassword(
      @Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest
  ) {

    userService.forgotPassword(forgotPasswordRequest);
    return ResponseDTO.of(ResponseType.FORGOT_PASSWORD_EMAIL_SENT);
  }

  @PostMapping("reset-password")
  public ResponseDTO<Void> resetPassword(
      @RequestParam String forgotPasswordToken,
      @Valid @RequestBody ResetPasswordRequest resetPasswordRequest
  ) {

    userService.resetPassword(resetPasswordRequest, forgotPasswordToken);
    return ResponseDTO.of(ResponseType.PASSWORD_RESET_SUCCESSFULLY);
  }

  @DeleteMapping("delete/{id}")
  public ResponseDTO<String> deleteUserById(@PathVariable Integer id ){
    userService.deleteUserById(id);
    return ResponseDTO.success("User Deleted successfully");
  }

  // active to inactive
  @PutMapping("status/{id}")
  public ResponseDTO<String> updateStatus(@RequestParam String token , @PathVariable Integer id){
    UserResponseDTO user = strablSessionDao.getUserProfileForSession(token);
    userService.updateStatus(id,INACTIVE);
    return ResponseDTO.success("status changed successfully");
  }

  @GetMapping("/getSellerItems")
  public ResponseDTO<List<ProductResponse>> getSellerItems(
          @RequestParam Integer pageNumber,
          @RequestParam Integer pageSize,
          @RequestParam Integer productId) {
    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, 6);
    return ResponseDTO.success(userService.getSellerItems(productId, pagedResponseRequest));
  }
}
