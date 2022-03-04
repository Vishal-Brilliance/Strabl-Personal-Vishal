package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class ReachedApiKeyLimitsException extends StrablException {

  private static final long serialVersionUID = 4494274147125866L;

  public ReachedApiKeyLimitsException(ResponseType responseType) {
    super(responseType);
  }

  public static ReachedApiKeyLimitsException of(ResponseType responseType) {
    return new ReachedApiKeyLimitsException(responseType);
  }
}
