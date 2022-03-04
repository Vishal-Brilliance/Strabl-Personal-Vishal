package com.strabl.service.gateway.facade.impl;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.request.ProductReviewRequest;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.ProductReviewResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.service.gateway.client.ProductServiceClient;
import com.strabl.service.gateway.facade.AuthServiceFacade;
import com.strabl.service.gateway.facade.ProductServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceFacadeImpl implements ProductServiceFacade {

  private final AuthServiceFacade authServiceFacade;
  private final ProductServiceClient productServiceClient;

  @Override
  public ResponseDTO<ProductResponse> getProduct(Integer productId) {
    return productServiceClient.getProduct(productId);
  }

  @Override
  public ResponseDTO<ProductResponse> newProduct(CreateProductRequest createProductRequest) {
    return productServiceClient.newProduct(authServiceFacade.getSignedInUser().getId(), createProductRequest);
  }

  @Override
  public ResponseDTO<List<ProductResponse>> getProducts(OfferingType offeringType, Integer pageNumber, Integer pageSize) {
    return productServiceClient.getProducts(offeringType, pageNumber, pageSize);
  }

  @Override
  public ResponseDTO<List<ProductResponse>> searchProducts(String searchTerm, Integer pageNumber, Integer pageSize) {
    return productServiceClient.searchProductsByTagsAndTitle(searchTerm, pageNumber, pageSize);
  }

  @Override
  public ResponseDTO<List<CategoryDTO>> getCategoriesByType(OfferingType type, Integer pageNumber, Integer pageSize) {
    return productServiceClient.getCategoriesByType(type, pageNumber, pageSize);
  }

  @Override
  public ResponseDTO<List<ProductResponse>> getProductsBy(String category, Integer pageNumber, Integer pageSize) {
    return productServiceClient.getProductsByCategory(category, pageNumber, pageSize);
  }

  @Override
  public ResponseDTO<List<ProductResponse>> getFeaturedProductsBy(OfferingType offeringType) {

    return productServiceClient.getFeaturedProductsBy(offeringType);
  }

  @Override
  public ResponseDTO<ProductReviewResponse> addProductReview(String productSlug, ProductReviewRequest productReviewRequest) {

    return productServiceClient.addProductReview(productSlug, authServiceFacade.getSignedInUser().getId(), productReviewRequest);
  }

  @Override
  public ResponseDTO<List<ProductReviewResponse>> getProductReviews(String productSlug, Integer pageNumber, Integer pageSize) {

    return productServiceClient.getProductReviews(productSlug, pageNumber, pageSize);
  }

  @Override
  public ResponseDTO<List<ProductResponse>> getUnapprovedProducts(Integer pageNumber, Integer pageSize) {

    return productServiceClient.getUnapprovedProducts(pageNumber, pageSize);
  }

  @Override
  public ResponseDTO<ProductResponse> setProductApprovalStatus(Integer productId, boolean approvalStatus) {

    return approvalStatus
        ? productServiceClient.approveProduct(productId)
        : productServiceClient.disapproveProduct(productId);
  }

  @Override
  public ResponseDTO<List<ProductReviewResponse>> getUnapprovedProductReviews(Integer pageNumber, Integer pageSize) {

    return productServiceClient.getUnapprovedProductReviews(pageNumber, pageSize);
  }

  @Override
  public ResponseDTO<ProductReviewResponse> setProductReviewApprovalStatus(Integer reviewId, boolean approvalStatus) {

    return approvalStatus
        ? productServiceClient.approveProductReview(reviewId)
        : productServiceClient.disapproveProductReview(reviewId);
  }
}
