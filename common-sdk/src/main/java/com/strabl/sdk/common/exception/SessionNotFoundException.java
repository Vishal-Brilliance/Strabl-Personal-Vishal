package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class SessionNotFoundException extends StrablException {

  private static final long serialVersionUID = -159959155770053691L;

  public SessionNotFoundException(ResponseType responseType) {
    super(responseType);
  }

  public static SessionNotFoundException of(ResponseType responseType) {
    return new SessionNotFoundException(responseType);
  }
}
