package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class PlanNotExistException extends StrablException {

  private static final long serialVersionUID = 1;

  public PlanNotExistException(ResponseType responseType) {
    super(responseType);
  }

  public static PlanNotExistException of(ResponseType responseType) {
    return new PlanNotExistException(responseType);
  }
}
