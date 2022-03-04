package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class ValidationException extends StrablException {

  private static final long serialVersionUID = 3111522606413076016L;

  public ValidationException(ResponseType responseType) {
    super(responseType);
  }

  public static ValidationException of(ResponseType responseType) {
    return new ValidationException(responseType);
  }
}
