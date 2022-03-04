package com.strabl.service.product.service.impl;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.dao.ProductDao;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.mapper.ProductMapper;
import com.strabl.service.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

  private static final Integer FEATURED_PRODUCTS_COUNT = 6;

  private final ProductDao productDao;
  private final UserDao userDao;

  public ProductResponse getProductBySlug(String slug) {

    return ProductMapper.toProductResponse(productDao.findBySlug(slug));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ProductResponse addProduct(CreateProductRequest createProductRequest, Integer userId) {

    User user = userDao.findByIdOrThrow(userId);

    if (!user.getIsEnabled()) {
      throw StrablException.of(ResponseType.USER_NOT_VERIFIED_OR_ENABLED);
    }
// 1. check if user is not seller
    if(!user.getIsseller()){
      throw StrablException.of(ResponseType.BAD_REQUEST);
    }

    Product product = ProductMapper.toProductEntityFrom(createProductRequest, user);
    Product savedProduct = productDao.create(product);

    return ProductMapper.toProductResponse(savedProduct);
  }

  @Override
  public Page<ProductResponse> getProducts(OfferingType offeringType, PagedResponseRequest pagedResponseRequest) {

    return productDao.searchByOfferingType(offeringType, pagedResponseRequest)
            .map(ProductMapper::toProductResponse);
  }

  @Override
  public Page<ProductResponse> getProductsBy(String category, PagedResponseRequest pagedResponseRequest) {

    return  productDao.getByCategory(category, pagedResponseRequest)
            .map(ProductMapper::toProductResponse);
  }

  @Override
  public Page<ProductResponse> getFeaturedProducts(OfferingType offeringType) {

    return productDao.getFeaturedRandomly(offeringType, FEATURED_PRODUCTS_COUNT)
            .map(ProductMapper::toProductResponse);
  }

  @Override
  public Page<ProductResponse> getUnapprovedProducts(PagedResponseRequest pagedResponseRequest) {

    return productDao.getUnapprovedProducts(pagedResponseRequest)
            .map(ProductMapper::toProductResponse);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ProductResponse updateApprovalStatus(Integer productId, boolean approvalStatus) {

    return ProductMapper.toProductResponse(productDao.updateApprovalStatus(productId, approvalStatus));
  }

  @Override
  public void deleteProductById(Integer id) {
    productDao.deleteProductById(id);
  }

  @Override
  @Transactional
  public void addProductToCart(Integer productId, Integer userId) {
    productDao.addProductToCart(productId, userId);
  }

  @Override
  public Page<ProductResponse> getProductFromCart(Integer userId, PagedResponseRequest pagedResponseRequest) { // getAllProduct
    return productDao.getProductFromCart(userId, pagedResponseRequest);
  }

  @Override
  @Transactional
  public void deleteProductFromCart(Integer productId, Integer userId) {
    productDao.deleteProductFromCart(productId, userId);
  }

  @Override
  public Set<ProductResponse> getRecentlyAddedProducts(Integer userId) {
    return productDao.getRecentlyAddedProducts(userId);
  }

  @Override
  @Transactional
  public void recentlyAddedProducts(Integer productId, Integer userId) {
    productDao.recentlyAddedProducts(productId, userId);
  }

  @Override
  public Page<ProductResponse> getFromSubCategory(Integer subcategory_id, PagedResponseRequest pagedResponseRequest) {
    return productDao.getFromSubCategory(subcategory_id ,pagedResponseRequest)
            .map(ProductMapper::toProductResponse);
  }
}
