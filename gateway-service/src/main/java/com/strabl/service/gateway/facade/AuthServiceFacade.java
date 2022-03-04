package com.strabl.service.gateway.facade;

import com.strabl.sdk.common.dto.response.UserResponseDTO;

public interface AuthServiceFacade {

  UserResponseDTO getSignedInUser();
}
