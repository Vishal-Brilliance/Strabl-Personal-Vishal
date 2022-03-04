package com.strabl.service.user.service.impl;

import com.strabl.sdk.common.dto.event.EventRequest;
import com.strabl.sdk.common.dto.event.EventType;
import com.strabl.sdk.common.dto.event.ServiceName;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.*;
import com.strabl.sdk.common.dto.response.LoginResponse;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.common.util.AuthTokenUtil;
import com.strabl.sdk.common.util.CommonUtils;
import com.strabl.sdk.common.util.JsonUtils;
import com.strabl.sdk.common.util.RandomKeyGenerator;
import com.strabl.sdk.domain.dao.EmailVerificationTokenDao;
import com.strabl.sdk.domain.dao.ForgotPasswordTokenDao;
import com.strabl.sdk.domain.dao.ProductDao;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import com.strabl.sdk.domain.mapper.UserMapper;
import com.strabl.service.user.client.NotificationServiceClient;
import com.strabl.service.user.event.publisher.EventPublisher;
import com.strabl.service.user.service.SessionService;
import com.strabl.service.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final NotificationServiceClient notificationServiceClient;
  private final SessionService sessionService;
  private final UserDao userDao;
  private final EmailVerificationTokenDao emailVerificationTokenDao;
  private final ForgotPasswordTokenDao forgotPasswordTokenDao;
  private final ProductDao productDao;

  @Value("${security.jwt.secret-key}")
  private String jwtSecret;

  private final EventPublisher eventPublisher;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public UserResponseDTO register(RegisterRequest registerRequest) {

    CommonUtils.checkEmail(registerRequest.getEmail());
    userDao.verifyEmailDoesNotExist(registerRequest.getEmail());

    registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

    User entity = UserMapper.toEntity(registerRequest);
    User savedUser = userDao.save(entity);

    String verificationToken = generateRandomToken();
    emailVerificationTokenDao.addTokenForUser(verificationToken, savedUser.getId());

    notificationServiceClient.sendEmail(EventType.SEND_SIGNUP_EMAIL,
        new SendEmailRequest.Builder(savedUser.getEmail())
            .setDynamicValueForTemplate(EmailTemplateKeys.FULL_NAME, savedUser.getFullName() + " " )
            .setDynamicValueForTemplate(EmailTemplateKeys.TOKEN, verificationToken)
            .build()
    );

    return UserMapper.toUserResponseDTO(savedUser);
  }

  private String generateRandomToken() {
    return RandomKeyGenerator.generateRandomString(128);
  }

  @Override
  public void verify(String token) {

    Integer userId = emailVerificationTokenDao.getUserIdFor(token);
    User user = userDao.findByIdOrThrow(userId);

    user.setIsEnabled(Boolean.TRUE);
    user.setStatus(UserStatus.ACTIVE);

    User savedUser = userDao.update(user);

    emailVerificationTokenDao.remove(token);

    notificationServiceClient.sendEmail(EventType.WELCOME_EMAIL,
        new SendEmailRequest.Builder(savedUser.getEmail())
            .setDynamicValueForTemplate(EmailTemplateKeys.FULL_NAME, savedUser.getFullName() + " " )
            .build()
    );
  }

  @Override
  public LoginResponse login(LoginRequest loginRequest) {

    CommonUtils.checkEmail(loginRequest.getEmail());

    User entity = userDao.findByEmailOrThrow(loginRequest.getEmail());

    if (!entity.getIsEnabled()) {
      throw StrablException.of(ResponseType.USER_NOT_VERIFIED_OR_ENABLED);
    }

    if (matchesLegacyPassword(loginRequest.getPassword(), entity.getPassword())) {
      entity.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
      userDao.save(entity);
    }

    if (passwordEncoder.matches(loginRequest.getPassword(), entity.getPassword())) {

      UserResponseDTO profile = UserMapper.toUserResponseDTO(entity);

      return LoginResponse.builder()
          .profile(profile)
          .auth(sessionService.createFreshTokensPair(profile))
          .build();
    } else {
      throw StrablException.of(ResponseType.BAD_CREDENTIALS);
    }
  }

  private boolean matchesLegacyPassword(String enteredPassword, String legacyHashedPassword) {
    return DigestUtils.sha1Hex(enteredPassword).equals(legacyHashedPassword);
  }

  @Override
  @Transactional
  public void logout(String sessionToken) {

    AuthTokenUtil.validateJwtAndGetSubject(sessionToken, jwtSecret);
    sessionService.invalidatePairBySessionToken(sessionToken);
  }

  @Override
  public void forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {

    User user = userDao.findByEmailOrThrow(forgotPasswordRequest.getEmail());

    String forgotPasswordToken = generateRandomToken();

    forgotPasswordTokenDao.addTokenForUser(forgotPasswordToken, user.getId());

    notificationServiceClient.sendEmail(EventType.FORGOT_PASSWORD,
        new SendEmailRequest.Builder(user.getEmail())
            .setDynamicValueForTemplate(EmailTemplateKeys.FULL_NAME, user.getFullName() + " " )
            .setDynamicValueForTemplate(EmailTemplateKeys.TOKEN, forgotPasswordToken)
            .build()
    );
  }

  @Override
  @Transactional
  public void resetPassword(ResetPasswordRequest resetPasswordRequest, String forgotPasswordToken) {

    Integer userId = forgotPasswordTokenDao.getUserIdFor(forgotPasswordToken);

    User user = userDao.findByIdOrThrow(userId);
    user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));

    userDao.update(user);
    forgotPasswordTokenDao.remove(forgotPasswordToken);

    sessionService.invalidateAllPairsFor(userId);
  }

  @Override
  public LoginResponse refreshToken(String refreshToken) {

    Integer userId = Integer.valueOf(AuthTokenUtil.validateJwtAndGetSubject(refreshToken, jwtSecret));
    User entity = userDao.findByIdOrThrow(userId);

    if (!entity.getIsEnabled()) {
      throw StrablException.of(ResponseType.USER_NOT_VERIFIED_OR_ENABLED);
    }

    UserResponseDTO profile = UserMapper.toUserResponseDTO(entity);

    sessionService.invalidatePairByRefreshToken(refreshToken);

    return LoginResponse.builder()
        .profile(profile)
        .auth(sessionService.createFreshTokensPair(profile))
        .build();
  }

  @Override
  public void deleteUserById(Integer id) {
    userDao.deleteUserById(id);
  }

  @Override
  public void updateStatus(Integer id, UserStatus inactive) {
    UserMapper.toUserResponseDTO(userDao.updateStatus(id,inactive));
  }

  private EventRequest prepareEmailNotification(
      EventType eventType, String email, String fullName, String token) {

    SendEmailRequest sendEmailRequest =
        new SendEmailRequest.Builder(email)
            .setDynamicValueForTemplate(EmailTemplateKeys.FULL_NAME, fullName)
            .setDynamicValueForTemplate(EmailTemplateKeys.TOKEN, token)
            .build();

    return EventRequest.builder()
        .eventSource(ServiceName.USER_SERVICE)
        .eventConsumer(ServiceName.NOTIFICATION_SERVICE)
        .eventType(eventType)
        .data(JsonUtils.toJson(sendEmailRequest))
        .build();
  }

  @Override
  public Page<ProductResponse> getSellerItems(Integer productId, PagedResponseRequest pagedResponseRequest) {
    return productDao.getSellerItems(productId, pagedResponseRequest);
  }
}
