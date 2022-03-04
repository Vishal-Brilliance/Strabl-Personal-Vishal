package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class EmailAlreadyExistException extends StrablException {

  private static final long serialVersionUID = -1599959155770053691L;

  public EmailAlreadyExistException(ResponseType responseType) {
    super(responseType);
  }

  public static EmailAlreadyExistException of(ResponseType responseType) {
    return new EmailAlreadyExistException(responseType);
  }
}
