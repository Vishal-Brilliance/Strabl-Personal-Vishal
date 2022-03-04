package com.strabl.sdk.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class JsonUtils {

  private static ObjectMapper mapper = new ObjectMapper();

  private JsonUtils() {}

  public static String toJson(Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.error("Failed to convert Object to String", e.getStackTrace());
      return null;
    }
  }

  public static <O> O toObject(String str, Class<O> clazz) {
    try {
      return mapper.readValue(str, clazz);
    } catch (IOException e) {
      log.error("Failed to convert String to Object", e.getStackTrace());
      return null;
    }
  }

  public static <O> List<O> toObjectList(String str, Class<O> clazz) {
    try {
      return mapper.readValue(
          str, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    } catch (IOException e) {
      log.error("Failed to convert String to Object", e.getStackTrace());
      return null;
    }
  }

  public static <O> O convertTo(Object object, Class<O> clazz) {
    return mapper.convertValue(object, clazz);
  }
}
