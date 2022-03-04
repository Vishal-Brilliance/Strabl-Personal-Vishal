package com.strabl.sdk.domain.repository;

import com.strabl.sdk.domain.entity.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository
    extends JpaRepository<Classification, Integer>, JpaSpecificationExecutor<Classification> {
}
