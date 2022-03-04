package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class PlanAlreadyExistException extends StrablException {

  private static final long serialVersionUID = 1;

  public PlanAlreadyExistException(ResponseType responseType) {
    super(responseType);
  }

  public static PlanAlreadyExistException of(ResponseType responseType) {
    return new PlanAlreadyExistException(responseType);
  }
}
