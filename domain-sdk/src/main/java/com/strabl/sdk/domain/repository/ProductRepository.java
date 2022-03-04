package com.strabl.sdk.domain.repository;

import com.strabl.sdk.domain.entity.Product;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
    extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

  @Query("SELECT id FROM Product WHERE tbyb_id IS NOT NULL AND isFeatured = true AND isActive = true AND isApproved = true ORDER BY RAND()")
  Page<Integer> getTBYBRandomFeaturedIds(Pageable pageable);

  @Query("SELECT id FROM Product  WHERE classification_id IS NOT NULL AND isFeatured = true AND isActive = true AND isApproved = true ORDER BY RAND()")
  Page<Integer> getRentalRandomFeaturedIds(Pageable pageable);

}
