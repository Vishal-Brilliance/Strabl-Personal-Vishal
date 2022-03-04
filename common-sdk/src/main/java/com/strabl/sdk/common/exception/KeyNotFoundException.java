package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class KeyNotFoundException extends StrablException {

  private static final long serialVersionUID = -1599591557053691L;

  public KeyNotFoundException(ResponseType responseType) {
    super(responseType);
  }

  public static KeyNotFoundException of(ResponseType responseType) {
    return new KeyNotFoundException(responseType);
  }
}
