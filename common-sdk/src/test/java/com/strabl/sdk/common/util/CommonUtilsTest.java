package com.strabl.sdk.common.util;

import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.InvalidEmailException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CommonUtilsTest {

  @Test
  public void checkEmail_whenEmailIsValid() {
    String email = "abc@gmail.com";
    CommonUtils.checkEmail(email);
  }

  @Test
  public void checkEmail_whenEmailIsNotValid() {
    String email = "abcgmail.com";
    try {
      CommonUtils.checkEmail(email);
      fail("Junit checkEmail_whenEmailIsNotValid does not throw any exception");
    } catch (InvalidEmailException e) {
      ResponseType responseType = ResponseType.EMAIL_INVALID;
      assertEquals(e.getCode(), responseType.getCode());
      assertEquals(e.getMessage(), responseType.getMessage());
      return;
    }

    fail("Junit checkEmail_whenEmailIsNotValid does not throw InvalidEmailException ");
  }
}
