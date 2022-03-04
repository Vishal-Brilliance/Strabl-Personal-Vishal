package com.strabl.sdk.domain.repository;

import com.strabl.sdk.domain.entity.Product;

import java.time.Instant;
import java.util.List;

import feign.Param;
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

  @Query(nativeQuery = true, value = "SELECT p.id\n" +
          "FROM tags_product tp\n" +
          "         JOIN product p on p.id = tp.product_id\n" +
          "         JOIN tags t on t.id = tp.tags_id\n" +
          "WHERE tp.tags_id in (:tagsList) LIMIT 10")
  List<Integer> getRelatedProduct(@Param("tagsList") List<Integer> tagsList);

  @Query(nativeQuery = true, value = "SELECT COUNT(*) as quantity FROM cart_product cp WHERE cp.cart_id = ?1 AND cp.product_id = ?2")
  Integer getProductQuantityInCart(Integer cartId, Integer productId);
}
