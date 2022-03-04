package com.strabl.sdk.domain.dao;

public interface ForgotPasswordTokenDao {

  void addTokenForUser(String forgotPasswordToken, Integer userId);
  Integer getUserIdFor(String forgotPasswordToken);
  void remove(String forgotPasswordToken);
}
