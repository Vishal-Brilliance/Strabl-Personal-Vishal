package com.strabl.sdk.common.exception;

import com.strabl.sdk.common.error.ResponseType;
import lombok.Getter;

@Getter
public class IncompleteUserInfoForSessionRecordingException extends StrablException {

  private static final long serialVersionUID = -159959155770053691L;

  public IncompleteUserInfoForSessionRecordingException(ResponseType responseType) {
    super(responseType);
  }

  public static IncompleteUserInfoForSessionRecordingException of(ResponseType responseType) {
    return new IncompleteUserInfoForSessionRecordingException(responseType);
  }
}
