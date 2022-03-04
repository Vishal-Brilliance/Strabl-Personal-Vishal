package com.strabl.service.gateway.client;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.request.ProductReviewRequest;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.ProductReviewResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

  @GetMapping("v1/api/product/{productId}")
  ResponseDTO<ProductResponse> getProduct(@PathVariable Integer productId);

  @PostMapping("v1/api/product/new")
  ResponseDTO<ProductResponse> newProduct(@RequestParam Integer userId, @RequestBody CreateProductRequest createProductRequest);

  @GetMapping("v1/api/product/products")
  ResponseDTO<List<ProductResponse>> getProducts(@RequestParam OfferingType q, @RequestParam Integer pageNumber, @RequestParam Integer pageSize);

  @GetMapping("v1/api/product/products/by")
  ResponseDTO<List<ProductResponse>> getProductsByCategory(@RequestParam String category, @RequestParam Integer pageNumber, @RequestParam Integer pageSize);

  @GetMapping("v1/api/product/products/featured")
  ResponseDTO<List<ProductResponse>> getFeaturedProductsBy(@RequestParam OfferingType type);

  @GetMapping("v1/api/search/products")
  ResponseDTO<List<ProductResponse>> searchProductsByTagsAndTitle(@RequestParam String q, @RequestParam Integer pageNumber, @RequestParam Integer pageSize);

  @GetMapping("v1/api/category/categories")
  ResponseDTO<List<CategoryDTO>> getCategoriesByType(@RequestParam OfferingType type, @RequestParam Integer pageNumber, @RequestParam Integer pageSize);

  @PostMapping("v1/api/review/product/{productSlug}")
  ResponseDTO<ProductReviewResponse> addProductReview(@PathVariable String productSlug, @RequestParam Integer reviewerUserId, @RequestBody ProductReviewRequest productReviewRequest);

  @GetMapping("v1/api/review/product/{productSlug}")
  ResponseDTO<List<ProductReviewResponse>> getProductReviews(@PathVariable String productSlug, @RequestParam Integer pageNumber, @RequestParam Integer pageSize);

  @GetMapping("v1/api/product/products/unapproved")
  ResponseDTO<List<ProductResponse>> getUnapprovedProducts(@RequestParam Integer pageNumber, @RequestParam Integer pageSize);

  @PatchMapping("v1/api/product/{productId}/approve")
  ResponseDTO<ProductResponse> approveProduct(@PathVariable Integer productId);

  @PatchMapping("v1/api/product/{productId}/disapprove")
  ResponseDTO<ProductResponse> disapproveProduct(@PathVariable Integer productId);

  @GetMapping("v1/api/review/reviews/unapproved")
  ResponseDTO<List<ProductReviewResponse>> getUnapprovedProductReviews(@RequestParam Integer pageNumber, @RequestParam Integer pageSize);

  @PatchMapping("v1/api/review/{reviewId}/approve")
  ResponseDTO<ProductReviewResponse> approveProductReview(@PathVariable Integer reviewId);

  @PatchMapping("v1/api/review/{reviewId}/disapprove")
  ResponseDTO<ProductReviewResponse> disapproveProductReview(@PathVariable Integer reviewId);
}
