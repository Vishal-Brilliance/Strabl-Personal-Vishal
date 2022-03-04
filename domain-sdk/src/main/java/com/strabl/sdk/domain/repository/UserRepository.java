package com.strabl.sdk.domain.repository;

import com.strabl.sdk.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);

  @Query(nativeQuery=true, value=" SELECT * from user")
  Page<User> getAllCustomers(Pageable pageable);


}
