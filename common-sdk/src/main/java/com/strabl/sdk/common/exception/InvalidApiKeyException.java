package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class InvalidApiKeyException extends StrablException {

  private static final long serialVersionUID = 448494274147125866L;

  public InvalidApiKeyException(ResponseType responseType) {
    super(responseType);
  }

  public static InvalidApiKeyException of(ResponseType responseType) {
    return new InvalidApiKeyException(responseType);
  }
}
