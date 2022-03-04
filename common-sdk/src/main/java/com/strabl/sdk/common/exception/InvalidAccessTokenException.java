package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class InvalidAccessTokenException extends StrablException {

  private static final long serialVersionUID = 4484943274147125866L;

  public InvalidAccessTokenException(ResponseType responseType) {
    super(responseType);
  }

  public static InvalidAccessTokenException of(ResponseType responseType) {
    return new InvalidAccessTokenException(responseType);
  }
}
