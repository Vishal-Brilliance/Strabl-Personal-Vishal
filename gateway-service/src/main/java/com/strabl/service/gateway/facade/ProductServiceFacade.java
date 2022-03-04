package com.strabl.service.gateway.facade;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.request.ProductReviewRequest;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.ProductReviewResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;

import java.util.List;

public interface ProductServiceFacade {

  ResponseDTO<ProductResponse> getProduct(Integer productId);

  ResponseDTO<ProductResponse> newProduct(CreateProductRequest createProductRequest);

  ResponseDTO<List<ProductResponse>> getProducts(OfferingType offeringType, Integer pageNumber, Integer pageSize);

  ResponseDTO<List<ProductResponse>> searchProducts(String searchTerm, Integer pageNumber, Integer pageSize);

  ResponseDTO<List<CategoryDTO>> getCategoriesByType(OfferingType type, Integer pageNumber, Integer pageSize);

  ResponseDTO<List<ProductResponse>> getProductsBy(String category, Integer pageNumber, Integer pageSize);

  ResponseDTO<List<ProductResponse>> getFeaturedProductsBy(OfferingType offeringType);

  ResponseDTO<ProductReviewResponse> addProductReview(String productSlug, ProductReviewRequest productReviewRequest);

  ResponseDTO<List<ProductReviewResponse>> getProductReviews(String productSlug, Integer pageNumber, Integer pageSize);

  ResponseDTO<List<ProductResponse>> getUnapprovedProducts(Integer pageNumber, Integer pageSize);

  ResponseDTO<ProductResponse> setProductApprovalStatus(Integer productId, boolean approvalStatus);

  ResponseDTO<List<ProductReviewResponse>> getUnapprovedProductReviews(Integer pageNumber, Integer pageSize);

  ResponseDTO<ProductReviewResponse> setProductReviewApprovalStatus(Integer reviewId, boolean approvalStatus);
}
