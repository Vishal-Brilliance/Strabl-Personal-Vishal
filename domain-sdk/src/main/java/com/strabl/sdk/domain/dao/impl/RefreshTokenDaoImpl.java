package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.dao.RefreshTokenDao;
import com.strabl.sdk.domain.entity.RefreshToken;
import com.strabl.sdk.domain.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static com.strabl.sdk.domain.entity.RefreshToken.*;

@Service
@AllArgsConstructor
public class RefreshTokenDaoImpl implements RefreshTokenDao {

  RefreshTokenRepository refreshTokenRepository;

  @Override
  public RefreshToken get(String refreshToken) {

    return refreshTokenRepository.findById(refreshToken)
        .orElseThrow(() -> StrablException.of(ResponseType.REFRESH_TOKEN_NOT_FOUND));
  }

  @Override
  public void invalidate(String refreshToken) {

    refreshTokenRepository.deleteById(refreshToken);
  }

  @Override
  public void create(String refreshToken, Integer userId, String sessionToken, Instant expiresAt) {

    refreshTokenRepository.save(
        builder()
            .refreshToken(refreshToken)
            .userId(userId)
            .sessionToken(sessionToken)
            .expiresAt(expiresAt)
            .build()
    );
  }

  @Override
  public void invalidateAllSessionsFor(Integer userId) {

    refreshTokenRepository.deleteByUserId(userId);
  }

  @Override
  public void invalidateBy(String sessionToken) {

    refreshTokenRepository.deleteBySessionToken(sessionToken);
  }

  @Override
  @Transactional
  public void cleanOldTokens(Instant cleanUntil) {

    refreshTokenRepository.deleteExpiredTokensTill(cleanUntil);
  }
}
