package com.strabl.sdk.domain.repository;

import com.strabl.sdk.domain.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface RefreshTokenRepository
    extends JpaRepository<RefreshToken, String>, JpaSpecificationExecutor<RefreshToken> {

  void deleteByUserId(Integer userId);
  void deleteBySessionToken(String sessionToken);

  @Modifying
  @Query("DELETE FROM RefreshToken rf WHERE rf.expiresAt <= :cleanUntil")
  void deleteExpiredTokensTill(@Param("cleanUntil") Instant cleanUntil);
}
