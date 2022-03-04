package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StrablGatewayException extends RuntimeException {

  private static final long serialVersionUID = -107439861255969944L;

  private String code;
  private HttpStatus httpStatus;

  public StrablGatewayException(ResponseType responseType) {
    super(responseType.getMessage());
    this.code = responseType.getCode();
    this.httpStatus = HttpStatus.BAD_REQUEST;
  }

  public StrablGatewayException(ResponseType responseType, HttpStatus httpStatus) {
    super(responseType.getMessage());
    this.code = responseType.getCode();
    this.httpStatus = httpStatus;
  }

  public static StrablGatewayException of(ResponseType responseType) {
    return new StrablGatewayException(responseType);
  }

  public static StrablGatewayException of(ResponseType responseType, HttpStatus httpStatus) {
    return new StrablGatewayException(responseType, httpStatus);
  }
}
