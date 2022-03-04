package com.strabl.sdk.common.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.InvalidPhoneNumberException;

public class PhoneUtils {

  private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

  public static void checkPhone(String phone) {
    if (!phoneUtil.isValidNumber(getPhoneNumber(phone))) {
      throw InvalidPhoneNumberException.of(ResponseType.PHONE_NUMBER_INVALID);
    }
  }

  public static Phonenumber.PhoneNumber getPhoneNumber(String number) {
    try {
      return phoneUtil.parse(number, "");
    } catch (NumberParseException e) {
      throw InvalidPhoneNumberException.of(ResponseType.PHONE_NUMBER_INVALID);
    }
  }
}
