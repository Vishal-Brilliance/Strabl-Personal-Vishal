package com.strabl.sdk.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

@Getter
public enum ResponseType {
  // Success
  SUCCESS("2000", "Operation completed successfully", HttpStatus.OK),
  USER_ACCOUNT_VERIFIED("2001", "Congratulations! Your account has been verified.", HttpStatus.OK),
  PASSWORD_RESET_SUCCESSFULLY("2002", "Password has been reset successfully", HttpStatus.OK),
  FORGOT_PASSWORD_EMAIL_SENT("2002", "Forgot password email has been sent. Please check your inbox to reset the password.", HttpStatus.OK),
  LOGOUT("2003", "Logout completed successfully", HttpStatus.OK),

  // Errors
  NULL_POINTER_EXCEPTION("5001", "Something went wrong! Please check with admin.", HttpStatus.INTERNAL_SERVER_ERROR),
  SERVER_ERROR("5002", "Server error! Please check with admin.", HttpStatus.INTERNAL_SERVER_ERROR),
  TOKEN_ALREADY_EXISTS("5003", "Token already exists!", HttpStatus.INTERNAL_SERVER_ERROR),
  NO_EMAIL_TEMPLATE_FOUND("5004", "Email template does not exist. Can't send email.", HttpStatus.INTERNAL_SERVER_ERROR),

  SESSION_EXPIRED("4010", "Session has expired, please renew session by refreshing the token or login again.", HttpStatus.UNAUTHORIZED),
  INVALID_AUTH_TOKEN("4011", "Session token could not be validated. Please login to get a new token.", HttpStatus.UNAUTHORIZED),
  SESSION_NOT_FOUND("4012", "Session not found. Please login to get a new token.", HttpStatus.UNAUTHORIZED),
  UNKNOWN_JWT_ERROR("4013", "Unknown error occurred while parsing/validating JWT.", HttpStatus.INTERNAL_SERVER_ERROR),
  REFRESH_TOKEN_NOT_FOUND("4014", "Refresh token not found. Please login to get a new token.", HttpStatus.UNAUTHORIZED),

  ENDPOINT_NOT_FOUND("4001", "Endpoint is not available", HttpStatus.NOT_FOUND),
  REQUEST_BODY_INVALID("4002", "Request body is invalid", HttpStatus.BAD_REQUEST),
  PHONE_NUMBER_INVALID("4105", "Phone number is invalid", HttpStatus.BAD_REQUEST),
  EMAIL_INVALID("4106", "Email is invalid", HttpStatus.BAD_REQUEST),
  EMAIL_ALREADY_EXISTS("4107", "Email already saved in the system", HttpStatus.BAD_REQUEST),
  TEMPLATE_PLACEHOLDER_KEY_EMPTY("4109", "Please provide valid template placeholder key", HttpStatus.INTERNAL_SERVER_ERROR),
  USER_NOT_FOUND("4113", "User not found", HttpStatus.BAD_REQUEST),
  BAD_REQUEST("4115", "Operation failed. Please verify request and try again", HttpStatus.BAD_REQUEST),
  BAD_CREDENTIALS("4124", "Bad credentials. Please verify username and password.", HttpStatus.BAD_REQUEST),
  PAGE_SIZE_INVALID("4132", "Page index must not be less than or equal to zero!", HttpStatus.BAD_REQUEST),
  TOKEN_DOES_NOT_EXIST("4155", "Verification/Forgot Password token does not exist", HttpStatus.BAD_REQUEST),
  USER_NOT_VERIFIED_OR_ENABLED("4156", "User account is not verified/enabled. Please verify your email.", HttpStatus.BAD_REQUEST),
  PRODUCT_NOT_FOUND("4157", "Specified Product was not found", HttpStatus.NOT_FOUND),
  EMAIL_NOT_SENT("4158", "Email could not be sent", HttpStatus.INTERNAL_SERVER_ERROR),
  PRODUCT_REVIEW_NOT_FOUND("4159", "Product Review could not be found", HttpStatus.NOT_FOUND);

  private final String code;
  private final String message;
  private final HttpStatus httpStatus;

  ResponseType(String code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  public static ResponseType getByCode(String code) {
    return Stream.of(ResponseType.values())
        .filter(errorType -> errorType.getCode().equals(code))
        .findFirst()
        .orElse(SERVER_ERROR);
  }
}
