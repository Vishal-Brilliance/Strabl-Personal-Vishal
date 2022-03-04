package com.strabl.sdk.common.exception.handler;

import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.AuthorizationException;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.common.exception.StrablGatewayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  private void logException(String message, StringBuffer url, Exception e) {
    log.error("********************** Start Global Exception **********************");
    log.error("URL: {}", url);
    log.error(message, e);
    log.error("**********************  End Global Exception  **********************");
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ResponseDTO<Void>> handleError404(HttpServletRequest request, Exception e) {

    ResponseDTO<Void> responseDTO = ResponseDTO.badRequest(ResponseType.ENDPOINT_NOT_FOUND);

    HttpStatus httpStatus = responseDTO.getHttpStatus();
    responseDTO.setHttpStatus(null);
    return new ResponseEntity<>(responseDTO, httpStatus);
  }

  @ExceptionHandler(StrablException.class)
  public ResponseEntity<ResponseDTO<Void>> handleStrablException(
      HttpServletRequest req, StrablException e) {

    logException("Strabl exception", req.getRequestURL(), e);

    ResponseDTO<Void> responseDTO = new ResponseDTO<>();
    responseDTO.setCode(e.getCode());
    responseDTO.setMessage(e.getMessage());

    HttpStatus httpStatus = e.getHttpStatus();
    if (e instanceof AuthorizationException) {
      httpStatus = HttpStatus.UNAUTHORIZED;
    }

    return new ResponseEntity<>(responseDTO, httpStatus);
  }

  @ExceptionHandler(StrablGatewayException.class)
  public ResponseEntity<ResponseDTO<Void>> handleStrablGatewayException(
      HttpServletRequest req, StrablGatewayException e) {

    logException("Strabl exception", req.getRequestURL(), e);

    ResponseDTO<Void> responseDTO = new ResponseDTO<>();
    responseDTO.setCode(e.getCode());
    responseDTO.setMessage(e.getMessage());

    HttpStatus httpStatus = e.getHttpStatus();
    return new ResponseEntity<>(responseDTO, httpStatus);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ResponseDTO<Void>> handleRuntimeException(
      HttpServletRequest req, RuntimeException e) {

    logException("Runtime exception", req.getRequestURL(), e);

    String message = e.getMessage();

    ResponseDTO<Void> responseDTO = ResponseDTO.serverError();
    responseDTO.setMessage(message);

    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    responseDTO.setHttpStatus(null);
    return new ResponseEntity<>(responseDTO, httpStatus);
  }

  @ExceptionHandler(value = NullPointerException.class)
  public ResponseEntity<ResponseDTO<Void>> nullPointerExceptionHandler(
      HttpServletRequest req, NullPointerException e) {

    logException(e.getMessage(), req.getRequestURL(), e);

    ResponseDTO<Void> responseDTO = ResponseDTO.serverError(ResponseType.NULL_POINTER_EXCEPTION);

    responseDTO.setMessage(Optional.ofNullable(e.getMessage()).orElse(responseDTO.getMessage()));

    HttpStatus httpStatus = responseDTO.getHttpStatus();
    responseDTO.setHttpStatus(null);
    return new ResponseEntity<>(responseDTO, httpStatus);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseDTO<Void>> handleMethodValidationException(
      HttpServletRequest req, HttpServletResponse response, MethodArgumentNotValidException ex) {

    String validationErrorMessage = ex.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
        .collect(Collectors.joining(", "));

    ResponseDTO<Void> responseDTO = ResponseDTO.badRequest(validationErrorMessage);

    HttpStatus httpStatus = responseDTO.getHttpStatus();
    responseDTO.setHttpStatus(null);
    return new ResponseEntity<>(responseDTO, httpStatus);
  }
}
