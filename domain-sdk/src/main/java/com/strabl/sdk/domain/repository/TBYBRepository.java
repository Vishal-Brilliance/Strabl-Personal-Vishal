package com.strabl.sdk.domain.repository;

import com.strabl.sdk.domain.entity.TBYB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TBYBRepository
    extends JpaRepository<TBYB, Integer>, JpaSpecificationExecutor<TBYB> {
}
