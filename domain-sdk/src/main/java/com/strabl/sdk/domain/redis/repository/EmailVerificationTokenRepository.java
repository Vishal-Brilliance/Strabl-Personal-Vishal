package com.strabl.sdk.domain.redis.repository;

import com.strabl.sdk.domain.redis.document.EmailVerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationTokenRepository extends CrudRepository<EmailVerificationToken, String> {}