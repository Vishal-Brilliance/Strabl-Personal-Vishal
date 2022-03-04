package com.strabl.sdk.domain.dao;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.domain.entity.User;

import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserDao {

  User save(User user);

  User update(User user);

  void verifyEmailDoesNotExist(String workEmail);

  User findByEmail(String username);

  User findByEmailOrThrow(String username);

  User findByIdOrThrow(Integer id);

  Page<User> findAll(Specification<User> userSpecification, Pageable pageable);
  
  Page<User> findAll(Pageable pageable);

  Long getUserCount();

  Page<User> getAllCustomers(PagedResponseRequest pagedResponseRequest);

  void deleteUserById(Integer id);

  User updateStatus(Integer id, UserStatus inactive);
}
