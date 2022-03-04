package com.strabl.service.gateway.security;

import com.strabl.sdk.common.dto.request.LoginRequest;
import com.strabl.sdk.common.dto.response.LoginResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.service.gateway.client.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class UserServiceAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  UserServiceClient userServiceClient;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    ResponseDTO<LoginResponse> loginResponse = userServiceClient.login(
        new LoginRequest(email, password)
    );

    if (loginResponse.getData() == null || loginResponse.getData().getAuth() == null) {
      throw new BadCredentialsException(loginResponse.getMessage());
    }

    return new PreAuthenticatedAuthenticationToken(
        loginResponse.getData().getProfile(),
        loginResponse.getData().getAuth(),
        null // TODO: Need to implement authorities/roles
    );
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
