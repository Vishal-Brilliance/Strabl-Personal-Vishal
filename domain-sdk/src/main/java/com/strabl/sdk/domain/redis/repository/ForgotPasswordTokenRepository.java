package com.strabl.sdk.domain.redis.repository;

import com.strabl.sdk.domain.redis.document.ForgotPasswordToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordTokenRepository extends CrudRepository<ForgotPasswordToken, String> {}
