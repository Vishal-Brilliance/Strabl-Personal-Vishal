package com.strabl.service.gateway.api.signed;

import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.service.gateway.api.BaseController;
import com.strabl.service.gateway.facade.UserServiceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/api/user")
@AllArgsConstructor
@Api(
    tags = {"User Controller"},
    value =
        "This controller is responsible for all user operations where an access token is required")
public class UserController extends BaseController {

  private final UserServiceFacade userServiceFacade;

  @ApiOperation(value = "Logs out the user and invalidates the session")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "User logged out successfully",
          response = ResponseDTO.class)
  })
  @GetMapping("logout")
  public ResponseEntity<ResponseDTO<Void>> logout(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String sessionToken
  ) {

    return toResponseEntity(userServiceFacade.logout(sessionToken));
  }
}
