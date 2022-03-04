package com.strabl.service.user.service.impl;

import com.strabl.sdk.common.dto.response.AuthDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.common.util.AuthTokenUtil;
import com.strabl.sdk.domain.dao.RefreshTokenDao;
import com.strabl.sdk.domain.dao.StrablSessionDao;
import com.strabl.sdk.domain.entity.RefreshToken;
import com.strabl.service.user.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

  private final StrablSessionDao strablSessionDao;
  private final RefreshTokenDao refreshTokenDao;

  @Value("${security.jwt.secret-key}")
  private String jwtSecret;

  @Value("${security.session.session-validity-seconds}")
  private Long sessionValiditySeconds;

  @Value("${security.session.refresh-validity-seconds}")
  private Long refreshValiditySeconds;

  @Override
  public AuthDTO createFreshTokensPair(UserResponseDTO profile) {

    Instant baseTime = Instant.now();

    String token = AuthTokenUtil.createJwt(profile.getId().toString(), jwtSecret, baseTime.plusSeconds(sessionValiditySeconds));
    String refreshToken = AuthTokenUtil.createJwt(profile.getId().toString(), jwtSecret, baseTime.plusSeconds(refreshValiditySeconds));

    strablSessionDao.create(token, profile.getId(), sessionValiditySeconds, profile);
    refreshTokenDao.create(refreshToken, profile.getId(), token, baseTime.plusSeconds(refreshValiditySeconds));

    return AuthDTO.builder()
        .token(token)
        .refreshToken(refreshToken)
        .build();
  }

  @Override
  public void invalidatePairBySessionToken(String sessionToken) {

    strablSessionDao.invalidate(sessionToken);
    refreshTokenDao.invalidateBy(sessionToken);
  }

  @Override
  public void invalidateAllPairsFor(Integer userId) {

    strablSessionDao.invalidateAllSessionsFor(userId);
    refreshTokenDao.invalidateAllSessionsFor(userId);
  }

  @Override
  public void invalidatePairByRefreshToken(String refreshToken) {

    RefreshToken entity = refreshTokenDao.get(refreshToken);

    strablSessionDao.invalidate(entity.getSessionToken());
    refreshTokenDao.invalidate(refreshToken);
  }
}
