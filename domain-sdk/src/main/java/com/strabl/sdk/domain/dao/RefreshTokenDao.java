package com.strabl.sdk.domain.dao;

import com.strabl.sdk.domain.entity.RefreshToken;

import java.time.Instant;

public interface RefreshTokenDao {

  RefreshToken get(String refreshToken);

  void invalidate(String refreshToken);

  void create(String refreshToken, Integer userId, String sessionToken, Instant expiresAt);

  void invalidateAllSessionsFor(Integer userId);

  void invalidateBy(String sessionToken);

  void cleanOldTokens(Instant cleanUntil);
}
