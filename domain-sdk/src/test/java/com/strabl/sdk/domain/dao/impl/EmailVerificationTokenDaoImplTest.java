package com.strabl.sdk.domain.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.redis.document.EmailVerificationToken;
import com.strabl.sdk.domain.redis.repository.EmailVerificationTokenRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailVerificationTokenDaoImpl.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class EmailVerificationTokenDaoImplTest {
    @Autowired
    private EmailVerificationTokenDaoImpl emailVerificationTokenDaoImpl;

    @MockBean
    private EmailVerificationTokenRepository emailVerificationTokenRepository;

    @Test
    void testAddTokenForUser() {
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setToken("ABC123");
        emailVerificationToken.setUserId(123);
        when(this.emailVerificationTokenRepository.existsById((String) any())).thenReturn(false);
        when(this.emailVerificationTokenRepository.save((EmailVerificationToken) any())).thenReturn(emailVerificationToken);
        this.emailVerificationTokenDaoImpl.addTokenForUser("ABC123", 123);
        verify(this.emailVerificationTokenRepository).existsById((String) any());
        verify(this.emailVerificationTokenRepository).save((EmailVerificationToken) any());
    }

    @Test
    void testAddTokenForUser2() {
        when(this.emailVerificationTokenRepository.existsById((String) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        when(
                this.emailVerificationTokenRepository.save((com.strabl.sdk.domain.redis.document.EmailVerificationToken) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class, () -> this.emailVerificationTokenDaoImpl.addTokenForUser("ABC123", 123));
        verify(this.emailVerificationTokenRepository).existsById((String) any());
    }

    @Test
    void testGetUserIdFor() {
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setToken("ABC123");
        emailVerificationToken.setUserId(123);
        Optional<EmailVerificationToken> ofResult = Optional.of(emailVerificationToken);
        when(this.emailVerificationTokenRepository.findById((String) any())).thenReturn(ofResult);
        assertEquals(123, this.emailVerificationTokenDaoImpl.getUserIdFor("ABC123").intValue());
        verify(this.emailVerificationTokenRepository).findById((String) any());
    }

    @Test
    void testGetUserIdFor2() {
        when(this.emailVerificationTokenRepository.findById((String) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class, () -> this.emailVerificationTokenDaoImpl.getUserIdFor("ABC123"));
        verify(this.emailVerificationTokenRepository).findById((String) any());
    }

    @Test
    void testRemove() {
        doNothing().when(this.emailVerificationTokenRepository).deleteById((String) any());
        this.emailVerificationTokenDaoImpl.remove("ABC123");
        verify(this.emailVerificationTokenRepository).deleteById((String) any());
    }

    @Test
    void testRemove2() {
        doThrow(StrablException.of(ResponseType.SUCCESS)).when(this.emailVerificationTokenRepository)
                .deleteById((String) any());
        assertThrows(StrablException.class, () -> this.emailVerificationTokenDaoImpl.remove("ABC123"));
        verify(this.emailVerificationTokenRepository).deleteById((String) any());
    }
}

