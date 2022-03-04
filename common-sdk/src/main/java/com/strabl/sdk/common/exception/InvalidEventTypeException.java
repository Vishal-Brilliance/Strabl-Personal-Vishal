package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class InvalidEventTypeException extends StrablException {

  private static final long serialVersionUID = -1599959155770053691L;

  public InvalidEventTypeException(ResponseType responseType) {
    super(responseType);
  }

  public static InvalidEventTypeException of(ResponseType responseType) {
    return new InvalidEventTypeException(responseType);
  }
}
