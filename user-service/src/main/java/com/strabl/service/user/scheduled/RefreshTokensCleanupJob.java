package com.strabl.service.user.scheduled;

import com.strabl.sdk.domain.dao.RefreshTokenDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class RefreshTokensCleanupJob {

  private final RefreshTokenDao refreshTokenDao;

  @Value("${security.session.refresh-token-cleanup-job.ttl-after-expiry-seconds}")
  private String ttlOfExpiredRecords;

  @Scheduled(
      initialDelayString = "${security.session.refresh-token-cleanup-job.initial-delay-millis}",
      fixedDelayString = "${security.session.refresh-token-cleanup-job.delay-between-executions-millis}"
  )
  public void cleanOldRefreshTokens() {

    log.info("[CLEANING OLD REFRESH TOKENS] STARTING SCHEDULED REFRESH TOKEN TABLE CLEAN UP JOB");

    long ttlAfterExpiryInSeconds = Long.parseLong(ttlOfExpiredRecords);
    Instant cleanUntil = Instant.now().minusSeconds(ttlAfterExpiryInSeconds);

    log.info("[CLEANING OLD REFRESH TOKENS] WILL REMOVE ALL REFRESH TOKENS THAT HAVE EXPIRED ON OR BEFORE {}", cleanUntil);

    refreshTokenDao.cleanOldTokens(cleanUntil);

    log.info("[CLEANING OLD REFRESH TOKENS] JOB COMPLETED");
  }
}
