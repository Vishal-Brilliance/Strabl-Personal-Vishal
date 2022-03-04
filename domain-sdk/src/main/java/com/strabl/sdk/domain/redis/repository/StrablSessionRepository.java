package com.strabl.sdk.domain.redis.repository;

import com.strabl.sdk.domain.redis.document.StrablSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrablSessionRepository extends CrudRepository<StrablSession, String> {

  List<StrablSession> findByUserId(Integer userId);
}
