package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class BadCredentialsStrablException extends StrablGatewayException {

  private static final long serialVersionUID = -5041768298232707376L;

  public BadCredentialsStrablException(ResponseType responseType) {
    super(responseType);
  }

  public static BadCredentialsStrablException of(ResponseType responseType) {
    return new BadCredentialsStrablException(responseType);
  }
}
