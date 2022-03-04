package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.common.exception.EmailAlreadyExistException;
import com.strabl.sdk.common.exception.UserNotFoundException;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {

  @InjectMocks private UserDaoImpl userDaoService;

  @Mock private UserRepository userRepository;

  @Test
  public void signUpTest() {

    User user = new User();

    userDaoService.save(user);

    verify(userRepository).save(user);
  }

  @Test
  public void updateTest() {

    User user = new User();

    userDaoService.update(user);

    verify(userRepository).save(user);
  }

  @Ignore
  @Test
  public void verifyEmailDoesNotExistTest_whenEmailExist() {

    String email = "a@b.com";

//    doReturn(Boolean.TRUE).when(userRepository).existsByWorkEmail(email);

    assertThrows(
        EmailAlreadyExistException.class, () -> userDaoService.verifyEmailDoesNotExist(email));
  }

  @Test
  public void findByUsernameTest_whenUserExist() {

    String username = "a@b.com";

    Optional<User> optionalUser = Optional.of(new User());

    doReturn(optionalUser).when(userRepository).findByEmail(username);

    assertEquals(optionalUser.get(), userDaoService.findByEmail(username));
  }

  @Test
  public void findByUsernameTest_whenUserNotExist() {

    String username = "a@b.com";

    Optional<User> optionalUser = Optional.empty();

    doReturn(optionalUser).when(userRepository).findByEmail(username);

    assertNull(userDaoService.findByEmail(username));
  }

  @Test
  public void findCheckedByUsernameTest_whenUserExist() {

    String username = "a@b.com";

    Optional<User> optionalUser = Optional.of(new User());

    doReturn(optionalUser).when(userRepository).findByEmail(username);

    assertEquals(optionalUser.get(), userDaoService.findByEmailOrThrow(username));
  }

  @Test
  public void findCheckedByUsernameTest_whenUserNotExist() {

    String username = "a@b.com";

    Optional<User> optionalUser = Optional.empty();

    doReturn(optionalUser).when(userRepository).findByEmail(username);

    assertThrows(UserNotFoundException.class, () -> userDaoService.findByEmailOrThrow(username));
  }

  @Test
  public void findByIdTest_whenUserExist() {

    Integer id = 1;

    Optional<User> optionalUser = Optional.of(new User());

    doReturn(optionalUser).when(userRepository).findById(id);

    assertEquals(optionalUser.get(), userDaoService.findByIdOrThrow(id));
  }

  @Test
  public void findByIdTest_whenUserNotExist() {

    Integer id = 1;

    Optional<User> optionalUser = Optional.empty();

    doReturn(optionalUser).when(userRepository).findById(id);

    assertThrows(UserNotFoundException.class, () -> userDaoService.findByIdOrThrow(id));
  }
}
