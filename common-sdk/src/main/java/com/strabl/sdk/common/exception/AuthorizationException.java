package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class AuthorizationException extends StrablException {

  private static final long serialVersionUID = -15995977053691L;

  public AuthorizationException(ResponseType responseType) {
    super(responseType);
  }

  public static AuthorizationException of(ResponseType responseType) {
    return new AuthorizationException(responseType);
  }
}
