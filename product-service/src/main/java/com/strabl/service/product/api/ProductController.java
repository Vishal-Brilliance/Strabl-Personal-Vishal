package com.strabl.service.product.api;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.domain.dao.StrablSessionDao;
import com.strabl.sdk.domain.entity.Address;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.repository.ProductRepository;
import com.strabl.service.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import com.strabl.service.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("v1/api/product")
@AllArgsConstructor
public class ProductController {

  private static final Integer MAX_PAGE_SIZE = 25;

  private final ProductRepository productRepository;
  private final ProductService productService;

  @GetMapping("{slug}")
  public ResponseDTO<ProductResponse> getProductBySlug(
          @PathVariable String slug) {

    return ResponseDTO.success(productService.getProductBySlug(slug));
  }

  @PostMapping("new")
  public ResponseDTO<ProductResponse> newProduct(
          @RequestParam Integer userId,
          @Valid @RequestBody CreateProductRequest createProductRequest) {

    return ResponseDTO.success(productService.addProduct(createProductRequest, userId));
  }

  @GetMapping("products")
  public ResponseDTO<List<ProductResponse>> getProducts(
          @RequestParam OfferingType q,
          @RequestParam Integer pageNumber,
          @RequestParam Integer pageSize) {

    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(productService.getProducts(q, pagedResponseRequest));
  }

  @GetMapping("products/by")
  public ResponseDTO<List<ProductResponse>> getProductsByCategory(
          @RequestParam String category,
          @RequestParam Integer pageNumber,
          @RequestParam Integer pageSize) {

    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(productService.getProductsBy(category, pagedResponseRequest));
  }

  @GetMapping("products/featured")
  public ResponseDTO<List<ProductResponse>> getFeaturedProducts(
          @RequestParam OfferingType type
  ) {

    return ResponseDTO.success(productService.getFeaturedProducts(type));
  }

  @GetMapping("products/unapproved")
  public ResponseDTO<List<ProductResponse>> getUnapprovedProducts(
          @RequestParam Integer pageNumber,
          @RequestParam Integer pageSize
  ) {

    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(productService.getUnapprovedProducts(pagedResponseRequest));
  }

  @PatchMapping("{productId}/approve")
  public ResponseDTO<ProductResponse> approveProduct(
          @PathVariable Integer productId
  ) {

    return ResponseDTO.success(productService.updateApprovalStatus(productId, true));
  }

  @PatchMapping("{productId}/disapprove")
  public ResponseDTO<ProductResponse> disapproveProduct(
          @PathVariable Integer productId
  ) {

    return ResponseDTO.success(productService.updateApprovalStatus(productId, false));
  }

  @DeleteMapping("delete/{id}")
  public ResponseDTO<String> deleteProductById(@PathVariable Integer id) {
    productService.deleteProductById(id);
    return ResponseDTO.success("Product Deleted successfully");
  }

  @PostMapping("/addProductToCart")
  public ResponseDTO<Void> addProductToCart(@RequestParam Integer productId, @RequestParam Integer userId) {
    productService.addProductToCart(productId, userId);
    return ResponseDTO.success();
  }

  @GetMapping("/getProductFromCart")
  public ResponseDTO<List<ProductResponse>> getProductFromCart(
          @RequestParam Integer userId,
          @RequestParam Integer pageNumber,
          @RequestParam Integer pageSize) {
    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(productService.getProductFromCart(userId, pagedResponseRequest));
  }

  @DeleteMapping("/deleteProductToCart")
  public ResponseDTO<Void> deleteProductFromCart(@RequestParam Integer productId, @RequestParam Integer userId) {
    productService.deleteProductFromCart(productId, userId);
    return ResponseDTO.success();
  }

  //As a buyer i want to see 12 recently added product in buy
  @GetMapping("/recentlyAddedProducts")
  public ResponseDTO<Set<ProductResponse>> getRecentlyAddedProducts(@RequestParam Integer userId) {
    return ResponseDTO.success(productService.getRecentlyAddedProducts(userId));
  }

  @PostMapping("/recentlyAddedProducts")
  public ResponseDTO<Void> recentlyAddedProducts(@RequestParam Integer productId, @RequestParam Integer userId) {
    productService.recentlyAddedProducts(productId, userId);
    return ResponseDTO.success();
  }

  @GetMapping("/sub-category")
  public ResponseDTO<List<ProductResponse>> getProductBySubCategory(
          @RequestParam Integer subcategory_id,
          @RequestParam Integer pageNumber,
          @RequestParam Integer pageSize) {
    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(productService.getFromSubCategory(subcategory_id , pagedResponseRequest));

  }
}
