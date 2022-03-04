package com.strabl.service.gateway.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablGatewayException;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class FeignErrorDecoderConfiguration {

  @Bean
  ErrorDecoder feignErrorDecoder(ObjectMapper objectMapper) {
    return (methodKey, response) -> {
      try {
        ResponseDTO responseDTO = objectMapper.readValue(response.body().toString(), ResponseDTO.class);
        return new StrablGatewayException(ResponseType.getByCode(responseDTO.getCode()), HttpStatus.resolve(response.status()));
      } catch (Exception e) {
        return new StrablGatewayException(ResponseType.SERVER_ERROR);
      }
    };
  }
}
