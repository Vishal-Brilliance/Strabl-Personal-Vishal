package com.strabl.sdk.common.util;

import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;

public class AuthTokenUtil {

  public static final String BEARER_TOKEN_PREFIX = "Bearer ";

  private AuthTokenUtil() {}

  public static String validateJwtAndGetSubject(String token, String jwtSecret)
      throws StrablException {

    try {
      return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    } catch (ExpiredJwtException ex) {
      throw StrablException.of(ResponseType.SESSION_EXPIRED);
    } catch (JwtException ex) {
      throw StrablException.of(ResponseType.INVALID_AUTH_TOKEN);
    } catch (Exception ex) {
      throw StrablException.of(ResponseType.UNKNOWN_JWT_ERROR);
    }
  }

  public static String createJwt(String subject, String jwtSecret, Instant validity) {

    return Jwts.builder()
        .setSubject(subject)
        .setExpiration(Date.from(validity))
        .setIssuer("strabl")
        .setIssuedAt(Date.from(Instant.now()))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public static String parseBearerToken(String token) {

    if (StringUtils.isEmpty(token) || !token.startsWith(BEARER_TOKEN_PREFIX)) {
      throw StrablException.of(ResponseType.INVALID_AUTH_TOKEN);
    }

    return token.substring(BEARER_TOKEN_PREFIX.length());
  }
}
