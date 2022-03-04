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
import com.strabl.sdk.domain.redis.document.ForgotPasswordToken;
import com.strabl.sdk.domain.redis.repository.ForgotPasswordTokenRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ForgotPasswordTokenDaoImpl.class})
@ActiveProfiles({"DEV"})
@ExtendWith(SpringExtension.class)
class ForgotPasswordTokenDaoImplTest {
    @Autowired
    private ForgotPasswordTokenDaoImpl forgotPasswordTokenDaoImpl;

    @MockBean
    private ForgotPasswordTokenRepository forgotPasswordTokenRepository;

    @Test
    void testAddTokenForUser() {
        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
        forgotPasswordToken.setId("42");
        forgotPasswordToken.setUserId(123);
        when(this.forgotPasswordTokenRepository.existsById((String) any())).thenReturn(false);
        when(this.forgotPasswordTokenRepository.save((ForgotPasswordToken) any())).thenReturn(forgotPasswordToken);
        this.forgotPasswordTokenDaoImpl.addTokenForUser("ABC123", 123);
        verify(this.forgotPasswordTokenRepository).existsById((String) any());
        verify(this.forgotPasswordTokenRepository).save((ForgotPasswordToken) any());
    }

    @Test
    void testAddTokenForUser2() {
        when(this.forgotPasswordTokenRepository.existsById((String) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        when(this.forgotPasswordTokenRepository.save((com.strabl.sdk.domain.redis.document.ForgotPasswordToken) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class, () -> this.forgotPasswordTokenDaoImpl.addTokenForUser("ABC123", 123));
        verify(this.forgotPasswordTokenRepository).existsById((String) any());
    }

    @Test
    void testGetUserIdFor() {
        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
        forgotPasswordToken.setId("42");
        forgotPasswordToken.setUserId(123);
        Optional<ForgotPasswordToken> ofResult = Optional.of(forgotPasswordToken);
        when(this.forgotPasswordTokenRepository.findById((String) any())).thenReturn(ofResult);
        assertEquals(123, this.forgotPasswordTokenDaoImpl.getUserIdFor("ABC123").intValue());
        verify(this.forgotPasswordTokenRepository).findById((String) any());
    }

    @Test
    void testGetUserIdFor2() {
        when(this.forgotPasswordTokenRepository.findById((String) any()))
                .thenThrow(StrablException.of(ResponseType.SUCCESS));
        assertThrows(StrablException.class, () -> this.forgotPasswordTokenDaoImpl.getUserIdFor("ABC123"));
        verify(this.forgotPasswordTokenRepository).findById((String) any());
    }

    @Test
    void testRemove() {
        doNothing().when(this.forgotPasswordTokenRepository).deleteById((String) any());
        this.forgotPasswordTokenDaoImpl.remove("ABC123");
        verify(this.forgotPasswordTokenRepository).deleteById((String) any());
    }

    @Test
    void testRemove2() {
        doThrow(StrablException.of(ResponseType.SUCCESS)).when(this.forgotPasswordTokenRepository)
                .deleteById((String) any());
        assertThrows(StrablException.class, () -> this.forgotPasswordTokenDaoImpl.remove("ABC123"));
        verify(this.forgotPasswordTokenRepository).deleteById((String) any());
    }
}

