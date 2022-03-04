package com.strabl.service.gateway.security;

import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.common.util.AuthTokenUtil;
import com.strabl.sdk.domain.dao.StrablSessionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

  private final StrablSessionDao strablSessionDao;

  private static final String AUTH_HEADER_PREFIX = "Bearer ";

  @Value("${security.jwt.secret-key}")
  private String jwtSecret;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
      throws ServletException, IOException {

    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith(AUTH_HEADER_PREFIX)) {
      filterChain.doFilter(request, response);
      return;
    }

    final String token = authHeader.substring(AUTH_HEADER_PREFIX.length());

    AuthTokenUtil.validateJwtAndGetSubject(token, jwtSecret);

    UserResponseDTO userResponseDTO = strablSessionDao.getUserProfileForSession(token);

    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        userResponseDTO,
        null,
        null
    );

    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }
}
