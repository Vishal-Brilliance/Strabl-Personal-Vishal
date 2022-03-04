package com.strabl.service.gateway.api;

import com.strabl.sdk.common.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

  protected<T> ResponseEntity<ResponseDTO<T>> toResponseEntity(ResponseDTO<T> data) {
    HttpStatus httpStatus = data.getHttpStatus();
    data.setHttpStatus(null);
    return new ResponseEntity<>(data, httpStatus);
  }
}
