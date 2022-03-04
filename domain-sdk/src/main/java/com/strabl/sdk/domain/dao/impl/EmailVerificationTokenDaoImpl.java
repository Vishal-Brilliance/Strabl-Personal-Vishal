package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.dao.EmailVerificationTokenDao;
import com.strabl.sdk.domain.redis.document.EmailVerificationToken;
import com.strabl.sdk.domain.redis.repository.EmailVerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailVerificationTokenDaoImpl implements EmailVerificationTokenDao {

  private final EmailVerificationTokenRepository repository;

  @Override
  public void addTokenForUser(String emailVerificationToken, Integer userId) {

    if (repository.existsById(emailVerificationToken)) {
      throw StrablException.of(ResponseType.TOKEN_ALREADY_EXISTS);
    }

    repository.save(EmailVerificationToken.of(emailVerificationToken,userId));
  }

  @Override
  public Integer getUserIdFor(String emailVerificationToken) {

    return repository.findById(emailVerificationToken)
            .orElseThrow(() -> StrablException.of(ResponseType.TOKEN_DOES_NOT_EXIST))
            .getUserId();
  }

  @Override
  public void remove(String emailVerificationToken) {
    repository.deleteById(emailVerificationToken);
  }
}
