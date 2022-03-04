package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class InvalidPhoneNumberException extends StrablException {

  private static final long serialVersionUID = 3298473966869975759L;

  public InvalidPhoneNumberException(ResponseType responseType) {
    super(responseType);
  }

  public static InvalidPhoneNumberException of(ResponseType responseType) {
    return new InvalidPhoneNumberException(responseType);
  }
}
