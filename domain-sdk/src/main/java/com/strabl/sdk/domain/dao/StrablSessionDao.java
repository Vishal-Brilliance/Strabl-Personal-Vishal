package com.strabl.sdk.domain.dao;

import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.common.exception.StrablException;

public interface StrablSessionDao {

  void create(String token, Integer userId, Long expiration, UserResponseDTO profile);
  void invalidate(String token);
  UserResponseDTO getUserProfileForSession(String token) throws StrablException;
  void invalidateAllSessionsFor(Integer userId);
}
