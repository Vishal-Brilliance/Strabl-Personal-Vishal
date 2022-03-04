package com.strabl.service.user.service;

import com.strabl.sdk.common.dto.response.AuthDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;

public interface SessionService {

  AuthDTO createFreshTokensPair(UserResponseDTO profile);

  void invalidatePairBySessionToken(String sessionToken);

  void invalidateAllPairsFor(Integer userId);

  void invalidatePairByRefreshToken(String refreshToken);
}
