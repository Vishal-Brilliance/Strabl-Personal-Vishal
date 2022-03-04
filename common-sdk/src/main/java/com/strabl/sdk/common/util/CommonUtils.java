package com.strabl.sdk.common.util;

import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.InvalidEmailException;
import org.apache.commons.validator.routines.EmailValidator;

public class CommonUtils {

  private static EmailValidator validator = EmailValidator.getInstance();

  private CommonUtils() {}

  public static void checkEmail(String email) {
    if (!validator.isValid(email)) {
      throw InvalidEmailException.of(ResponseType.EMAIL_INVALID);
    }
  }
}
