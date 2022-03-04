package com.strabl.service.product.service;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;

import com.strabl.sdk.common.error.ResponseType;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Set;

public interface ProductService {

  ProductResponse getProductBySlug(String slug);

  ProductResponse addProduct(CreateProductRequest createProductRequest, Integer userId);

  Page<ProductResponse> getProducts(OfferingType offeringType, PagedResponseRequest pagedResponseRequest);

  Page<ProductResponse> getProductsBy(String category, PagedResponseRequest pagedResponseRequest);

  Page<ProductResponse> getFeaturedProducts(OfferingType offeringType);

  Page<ProductResponse> getUnapprovedProducts(PagedResponseRequest pagedResponseRequest);

  ProductResponse updateApprovalStatus(Integer productId, boolean approvalStatus);

    void deleteProductById(Integer id);

  void addProductToCart(Integer productId, Integer userId);

  Page<ProductResponse> getProductFromCart(Integer userId, PagedResponseRequest pagedResponseRequest);

  void deleteProductFromCart(Integer productId, Integer userId);

  Set<ProductResponse> getRecentlyAddedProducts(Integer userId);

  void recentlyAddedProducts(Integer productId, Integer userId);
  Page<ProductResponse> getFromSubCategory(Integer subcategory_id, PagedResponseRequest pagedResponseRequest);
}
