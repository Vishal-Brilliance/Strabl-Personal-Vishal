package com.strabl.sdk.domain.dao.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.entity.RefreshToken;
import com.strabl.sdk.domain.repository.RefreshTokenRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RefreshTokenDaoImpl.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class RefreshTokenDaoImplTest {
    @Autowired
    private RefreshTokenDaoImpl refreshTokenDaoImpl;

    @MockBean
    private RefreshTokenRepository refreshTokenRepository;

    @Test
    void testGet() {
        RefreshToken refreshToken = new RefreshToken();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        refreshToken.setExpiresAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        refreshToken.setRefreshToken("ABC123");
        refreshToken.setSessionToken("ABC123");
        refreshToken.setUserId(123);
        Optional<RefreshToken> ofResult = Optional.of(refreshToken);
        when(this.refreshTokenRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(refreshToken, this.refreshTokenDaoImpl.get("ABC123"));
        verify(this.refreshTokenRepository).findById((String) any());
    }

    @Test
    void testGet2() {
        when(this.refreshTokenRepository.findById((String) any())).thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class, () -> this.refreshTokenDaoImpl.get("ABC123"));
        verify(this.refreshTokenRepository).findById((String) any());
    }

    @Test
    void testInvalidate() {
        doNothing().when(this.refreshTokenRepository).deleteById((String) any());
        this.refreshTokenDaoImpl.invalidate("ABC123");
        verify(this.refreshTokenRepository).deleteById((String) any());
    }

    @Test
    void testInvalidate2() {
        doThrow(StrablException.of(ResponseType.SUCCESS)).when(this.refreshTokenRepository).deleteById((String) any());
        assertThrows(StrablException.class, () -> this.refreshTokenDaoImpl.invalidate("ABC123"));
        verify(this.refreshTokenRepository).deleteById((String) any());
    }

    @Test
    void testCreate() {
        RefreshToken refreshToken = new RefreshToken();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        refreshToken.setExpiresAt(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        refreshToken.setRefreshToken("ABC123");
        refreshToken.setSessionToken("ABC123");
        refreshToken.setUserId(123);
        when(this.refreshTokenRepository.save((RefreshToken) any())).thenReturn(refreshToken);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        this.refreshTokenDaoImpl.create("ABC123", 123, "ABC123", atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        verify(this.refreshTokenRepository).save((RefreshToken) any());
    }

    @Test
    void testCreate2() {
        when(this.refreshTokenRepository.save((RefreshToken) any())).thenThrow(StrablException.of(ResponseType.SUCCESS));
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(StrablException.class, () -> this.refreshTokenDaoImpl.create("ABC123", 123, "ABC123",
                atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        verify(this.refreshTokenRepository).save((RefreshToken) any());
    }

    @Test
    void testInvalidateAllSessionsFor() {
        doNothing().when(this.refreshTokenRepository).deleteByUserId((Integer) any());
        this.refreshTokenDaoImpl.invalidateAllSessionsFor(123);
        verify(this.refreshTokenRepository).deleteByUserId((Integer) any());
    }

    @Test
    void testInvalidateAllSessionsFor2() {
        doThrow(StrablException.of(ResponseType.SUCCESS)).when(this.refreshTokenRepository).deleteByUserId((Integer) any());
        assertThrows(StrablException.class, () -> this.refreshTokenDaoImpl.invalidateAllSessionsFor(123));
        verify(this.refreshTokenRepository).deleteByUserId((Integer) any());
    }

    @Test
    void testInvalidateBy() {
        doNothing().when(this.refreshTokenRepository).deleteBySessionToken((String) any());
        this.refreshTokenDaoImpl.invalidateBy("ABC123");
        verify(this.refreshTokenRepository).deleteBySessionToken((String) any());
    }

    @Test
    void testInvalidateBy2() {
        doThrow(StrablException.of(ResponseType.SUCCESS)).when(this.refreshTokenRepository)
                .deleteBySessionToken((String) any());
        assertThrows(StrablException.class, () -> this.refreshTokenDaoImpl.invalidateBy("ABC123"));
        verify(this.refreshTokenRepository).deleteBySessionToken((String) any());
    }

    @Test
    void testCleanOldTokens() {
        doNothing().when(this.refreshTokenRepository).deleteExpiredTokensTill((Instant) any());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        this.refreshTokenDaoImpl.cleanOldTokens(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        verify(this.refreshTokenRepository).deleteExpiredTokensTill((Instant) any());
    }

    @Test
    void testCleanOldTokens2() {
        doThrow(StrablException.of(ResponseType.SUCCESS)).when(this.refreshTokenRepository)
                .deleteExpiredTokensTill((Instant) any());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(StrablException.class,
                () -> this.refreshTokenDaoImpl.cleanOldTokens(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        verify(this.refreshTokenRepository).deleteExpiredTokensTill((Instant) any());
    }
}

