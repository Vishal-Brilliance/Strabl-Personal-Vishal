package com.strabl.sdk.common.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomKeyGenerator {

  private RandomKeyGenerator() {}

  public static String generateRandomString(int size) {
    return RandomStringUtils.randomAlphanumeric(size);
  }
}
