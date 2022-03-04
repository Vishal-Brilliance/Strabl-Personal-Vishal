package com.strabl.sdk.common.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.strabl.sdk.common.error.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {

  private String code;
  private String message;
  private HttpStatus httpStatus;
  private T data;
  private Integer pageSize;
  private Integer totalPages;
  private Integer pageNumber;
  private Long totalRecords;

  public ResponseDTO(T data) {
    this.data = data;
  }

  public static<T> ResponseDTO<T> success(T data) {
    ResponseDTO<T> responseDTO = of(ResponseType.SUCCESS);
    responseDTO.data = data;
    return responseDTO;
  }

  public static<T> ResponseDTO<List<T>> success(Page<T> page) {
    ResponseDTO<List<T>> responseDTO = of(ResponseType.SUCCESS);

    responseDTO.data = page.getContent();
    responseDTO.pageSize = page.getSize();
    responseDTO.pageNumber = page.getNumber() + 1;
    responseDTO.totalPages = page.getTotalPages();
    responseDTO.totalRecords = page.getTotalElements();

    return responseDTO;
  }

  public static ResponseDTO<Void> success() {
    ResponseDTO<Void> responseDTO = of(ResponseType.SUCCESS);
    responseDTO.setHttpStatus(HttpStatus.OK);
    return responseDTO;
  }

  public static ResponseDTO<Void> success(ResponseType responseType) {
    ResponseDTO<Void> responseDTO = of(responseType);
    responseDTO.setHttpStatus(HttpStatus.OK);
    return responseDTO;
  }

  public static ResponseDTO<Void> badRequest(String errorMessage) {
    ResponseDTO<Void> responseDTO = of(ResponseType.BAD_REQUEST);

    responseDTO.setCode(ResponseType.BAD_REQUEST.getCode());
    responseDTO.setMessage(errorMessage);
    responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST);

    return responseDTO;
  }

  public static ResponseDTO<Void> badRequest(ResponseType responseType) {

    ResponseDTO<Void> responseDTO = of(ResponseType.BAD_REQUEST);

    responseDTO.setCode(responseType.getCode());
    responseDTO.setMessage(responseType.getMessage());
    responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST);

    return responseDTO;
  }

  public static ResponseDTO<Void> serverError() {
    ResponseDTO<Void> responseDTO = of(ResponseType.SERVER_ERROR);
    responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    return responseDTO;
  }

  public static ResponseDTO<Void> serverError(ResponseType responseType) {
    ResponseDTO<Void> responseDTO = of(responseType);
    responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    return responseDTO;
  }

  public static<T> ResponseDTO<T> of(ResponseType responseType) {
    ResponseDTO<T> responseDTO = new ResponseDTO<>();

    responseDTO.setCode(responseType.getCode());
    responseDTO.setMessage(responseType.getMessage());
    responseDTO.setHttpStatus(responseType.getHttpStatus());

    return responseDTO;
  }
}
