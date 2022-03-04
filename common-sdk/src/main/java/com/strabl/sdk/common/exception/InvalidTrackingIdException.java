package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class InvalidTrackingIdException extends StrablException {

  private static final long serialVersionUID = -159959770053691L;

  public InvalidTrackingIdException(ResponseType responseType) {
    super(responseType);
  }

  public static InvalidTrackingIdException of(ResponseType responseType) {
    return new InvalidTrackingIdException(responseType);
  }
}
