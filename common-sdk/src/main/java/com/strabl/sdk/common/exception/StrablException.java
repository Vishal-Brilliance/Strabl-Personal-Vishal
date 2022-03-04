package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StrablException extends RuntimeException {

  private static final long serialVersionUID = 842682465752804358L;

  private String code;
  private HttpStatus httpStatus;

  public StrablException(ResponseType responseType) {
    super(responseType.getMessage());
    this.code = responseType.getCode();
    this.httpStatus = HttpStatus.BAD_REQUEST;
  }

  public StrablException(ResponseType responseType, HttpStatus httpStatus) {
    super(responseType.getMessage());
    this.code = responseType.getCode();
    this.httpStatus = httpStatus;
  }

  public static StrablException of(ResponseType responseType) {
    return new StrablException(responseType);
  }

  public static StrablException of(ResponseType responseType, HttpStatus httpStatus) {
    return new StrablException(responseType, httpStatus);
  }
}
