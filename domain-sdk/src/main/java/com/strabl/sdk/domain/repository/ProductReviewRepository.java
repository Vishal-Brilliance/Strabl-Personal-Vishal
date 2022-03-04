package com.strabl.sdk.domain.repository;

import com.strabl.sdk.domain.entity.ProductReview;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductReviewRepository
    extends JpaRepository<ProductReview, Integer>, JpaSpecificationExecutor<ProductReview> {


}
