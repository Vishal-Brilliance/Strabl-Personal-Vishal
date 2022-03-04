package com.strabl.sdk.domain.dao;

public interface EmailVerificationTokenDao {

  void addTokenForUser(String emailVerificationToken, Integer userId);
  Integer getUserIdFor(String emailVerificationToken);
  void remove(String emailVerificationToken);
}
