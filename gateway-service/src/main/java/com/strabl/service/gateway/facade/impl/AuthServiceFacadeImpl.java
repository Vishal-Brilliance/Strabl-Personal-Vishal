package com.strabl.service.gateway.facade.impl;

import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.service.gateway.facade.AuthServiceFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceFacadeImpl implements AuthServiceFacade {

  @Override
  public UserResponseDTO getSignedInUser() {

    try {
      return (UserResponseDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (Exception ex) {
      throw StrablException.of(ResponseType.SESSION_NOT_FOUND);
    }
  }
}
