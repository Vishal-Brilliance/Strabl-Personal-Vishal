package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class UserNotFoundException extends StrablException {

  private static final long serialVersionUID = 5717594923772563379L;

  public UserNotFoundException(ResponseType responseType) {
    super(responseType);
  }

  public static UserNotFoundException of(ResponseType responseType) {
    return new UserNotFoundException(responseType);
  }
}
