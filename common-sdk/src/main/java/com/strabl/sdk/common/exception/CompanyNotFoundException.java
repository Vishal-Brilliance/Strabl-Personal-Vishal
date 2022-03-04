package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class CompanyNotFoundException extends StrablException {

  private static final long serialVersionUID = -15995915570053691L;

  public CompanyNotFoundException(ResponseType responseType) {
    super(responseType);
  }

  public static CompanyNotFoundException of(ResponseType responseType) {
    return new CompanyNotFoundException(responseType);
  }
}
