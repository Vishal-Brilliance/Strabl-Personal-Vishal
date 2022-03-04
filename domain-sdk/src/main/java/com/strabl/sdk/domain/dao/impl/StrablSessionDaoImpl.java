package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.dao.StrablSessionDao;
import com.strabl.sdk.domain.redis.document.StrablSession;
import com.strabl.sdk.domain.redis.repository.StrablSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StrablSessionDaoImpl implements StrablSessionDao {

  private final StrablSessionRepository strablSessionRepository;

  @Override
  public void create(String token, Integer userId, Long expiration, UserResponseDTO profile) {

    strablSessionRepository.save(
        StrablSession.of(token, userId, expiration, profile)
    );
  }

  @Override
  public void invalidate(String token) {

    strablSessionRepository.deleteById(token);
  }

  @Override
  public UserResponseDTO getUserProfileForSession(String token) throws StrablException {

    return strablSessionRepository.findById(token)
        .orElseThrow(() -> StrablException.of(ResponseType.SESSION_NOT_FOUND))
        .getProfile();
  }

  @Override
  public void invalidateAllSessionsFor(Integer userId) {

    strablSessionRepository.findByUserId(userId)
        .forEach(session -> strablSessionRepository.deleteById(session.getJwtToken()));
  }
}
