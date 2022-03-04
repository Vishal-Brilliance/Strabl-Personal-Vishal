package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.EmailAlreadyExistException;
import com.strabl.sdk.common.exception.UserNotFoundException;
import com.strabl.sdk.domain.criteria.page.StrablPageable;
import com.strabl.sdk.domain.criteria.specification.CategorySpecification;
import com.strabl.sdk.domain.criteria.specification.ProductSpecification;
import com.strabl.sdk.domain.criteria.specification.UserSpecification;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import com.strabl.sdk.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.strabl.sdk.common.error.ResponseType.USER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

  private final UserRepository userRepository;

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public User update(User user) {
    user.setUpdatedAt(Instant.now());
    return userRepository.save(user);
  }

  @Override
  public void verifyEmailDoesNotExist(String email) {
    if (userRepository.existsByEmail(email)) {
      throw EmailAlreadyExistException.of(ResponseType.EMAIL_ALREADY_EXISTS);
    }
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElse(null);
  }

  @Override
  public User findByEmailOrThrow(String email) {
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> UserNotFoundException.of(USER_NOT_FOUND));
  }

  @Override
  public User findByIdOrThrow(Integer id) {
    return userRepository
            .findById(id)
        .orElseThrow(() -> UserNotFoundException.of(USER_NOT_FOUND));
  }

  @Override
  public Page<User> findAll(Specification<User> userSpecification, Pageable pageable) {
    return userRepository.findAll(userSpecification, pageable);
  }


  @Override
  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  public Long getUserCount() {
    return userRepository.count();
  }


  @Override
  public Page<User> getAllCustomers( PagedResponseRequest pagedResponseRequest) {
   // Specification<User> specification = UserSpecification.getAllUser();
    Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);
    return userRepository.findAll(pageable);
  }

  @Override
  public void deleteUserById(Integer id) {
    userRepository.deleteById(id);
  }

  @Override
  public User updateStatus(Integer id, UserStatus inactive) {
    User user = this.findByIdOrThrow(id);
    user.setStatus(UserStatus.INACTIVE);
    return userRepository.save(user);
  }

}
