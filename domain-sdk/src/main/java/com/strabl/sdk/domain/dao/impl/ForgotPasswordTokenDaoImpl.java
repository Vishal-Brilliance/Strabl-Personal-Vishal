package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.dao.ForgotPasswordTokenDao;
import com.strabl.sdk.domain.redis.document.ForgotPasswordToken;
import com.strabl.sdk.domain.redis.repository.ForgotPasswordTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ForgotPasswordTokenDaoImpl implements ForgotPasswordTokenDao {

  private ForgotPasswordTokenRepository repository;

  @Override
  public void addTokenForUser(String forgotPasswordToken, Integer userId) {

    if (repository.existsById(forgotPasswordToken)) {
      throw StrablException.of(ResponseType.TOKEN_ALREADY_EXISTS);
    }

    repository.save(ForgotPasswordToken.of(forgotPasswordToken, userId));
  }

  @Override
  public Integer getUserIdFor(String forgotPasswordToken) {

    return repository.findById(forgotPasswordToken)
        .orElseThrow(() -> StrablException.of(ResponseType.TOKEN_DOES_NOT_EXIST))
        .getUserId();
  }

  @Override
  public void remove(String forgotPasswordToken) {

    repository.deleteById(forgotPasswordToken);
  }
}
