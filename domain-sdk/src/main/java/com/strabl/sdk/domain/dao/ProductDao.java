package com.strabl.sdk.domain.dao;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.domain.entity.Product;

import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.DoubleStream;

public interface ProductDao {

  Product findByIdOrThrow(Integer productId);
  Product findBySlug(String slug);
  Product create(Product product);
  Product update(Product product);
  Page<Product> searchByOfferingType(OfferingType offeringType, PagedResponseRequest pagedResponseRequest);
  Page<Product> searchByTagsAndTitle(String searchTerm, PagedResponseRequest pagedResponseRequest);
  Page<Product> getByCategory(String category, PagedResponseRequest pagedResponseRequest);
  Page<Product> getFeaturedRandomly(OfferingType offeringType, Integer count);
  Page<Product> getUnapprovedProducts(PagedResponseRequest pagedResponseRequest);
  Product updateApprovalStatus(Integer productId, boolean approvalStatus);
  void deleteProductById(Integer id);
  Page<Product> sellerProduct(Integer sellerId, PagedResponseRequest pagedResponseRequest);
  List<Product> getAllProductByCategory(String category);
  void addProductToCart(Integer productId, Integer userId);
  Page<ProductResponse> getProductFromCart(Integer userId, PagedResponseRequest pagedResponseRequest);
  void deleteProductFromCart(Integer productId, Integer userId);
  Page<ProductResponse> getSellerItems(Integer productId, PagedResponseRequest pagedResponseRequest);
  Set<ProductResponse> getRecentlyAddedProducts(Integer userId);
  void recentlyAddedProducts(Integer productId, Integer userId);
  Page<Product> getFromSubCategory(Integer subcategory_id, PagedResponseRequest pagedResponseRequest);
}
