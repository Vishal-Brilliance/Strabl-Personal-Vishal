package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class InvalidEmailException extends StrablException {

  private static final long serialVersionUID = 6342686539265522152L;

  public InvalidEmailException(ResponseType responseType) {
    super(responseType);
  }

  public static InvalidEmailException of(ResponseType responseType) {
    return new InvalidEmailException(responseType);
  }
}
