package com.strabl.service.gateway.api.anonymous;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.ProductReviewResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.service.gateway.api.BaseController;
import com.strabl.service.gateway.facade.ProductServiceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/public/api/product")
@AllArgsConstructor
@Api(
    tags = {"Public Product Controller"},
    value = "Allows you to fetch product or view products listing")
public class PublicProductController extends BaseController {

  private final ProductServiceFacade productServiceFacade;

  @ApiOperation(value = "Fetches a product using the product ID")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Product was found and returned successfully",
          response = ProductResponse.class),
      @ApiResponse(
          code = 404,
          message = "Product was not found",
          response = ResponseDTO.class),
      @ApiResponse(
          code = 400,
          message = "Invalid request parameters",
          response = ResponseDTO.class)
  })
  @GetMapping("{productId}")
  public ResponseEntity<ResponseDTO<ProductResponse>> getProduct(
      @PathVariable Integer productId) {

    return toResponseEntity(productServiceFacade.getProduct(productId));
  }

  @ApiOperation(value = "Fetches all products by offering type")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Products were found and returned successfully",
          response = ProductResponse.class,
          responseContainer = "List"),
      @ApiResponse(
          code = 400,
          message = "Invalid request parameters",
          response = ResponseDTO.class)
  })
  @GetMapping("products")
  public ResponseEntity<ResponseDTO<List<ProductResponse>>> getProducts(
      @RequestParam OfferingType q,
      @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
      @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

    return toResponseEntity(productServiceFacade.getProducts(q, pageNumber, pageSize));
  }

  @ApiOperation(value = "Searches products by matching search term to tags and title")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Products were found and returned successfully",
          response = ProductResponse.class,
          responseContainer = "List"),
      @ApiResponse(
          code = 400,
          message = "Invalid request parameters",
          response = ResponseDTO.class)
  })
  @GetMapping("search")
  public ResponseEntity<ResponseDTO<List<ProductResponse>>> searchProducts(
      @RequestParam String q,
      @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
      @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

    return toResponseEntity(productServiceFacade.searchProducts(q, pageNumber, pageSize));
  }

  @ApiOperation(value = "Lists categories by the offering type")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Categories were found and returned successfully",
          response = CategoryDTO.class,
          responseContainer = "List"),
      @ApiResponse(
          code = 400,
          message = "Invalid request parameters",
          response = ResponseDTO.class)
  })
  @GetMapping("categories")
  public ResponseEntity<ResponseDTO<List<CategoryDTO>>> getCategoriesByType(
      @RequestParam OfferingType type,
      @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
      @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

    return toResponseEntity(productServiceFacade.getCategoriesByType(type, pageNumber, pageSize));
  }

  @ApiOperation(value = "Gets products by category name")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Products were found and returned successfully",
          response = ProductResponse.class,
          responseContainer = "List"),
      @ApiResponse(
          code = 400,
          message = "Invalid request parameters",
          response = ResponseDTO.class)
  })
  @GetMapping("products/by")
  public ResponseEntity<ResponseDTO<List<ProductResponse>>> getProductsByCategory(
      @RequestParam String category,
      @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
      @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

    return toResponseEntity(productServiceFacade.getProductsBy(category, pageNumber, pageSize));
  }

  @ApiOperation(value = "Gets featured products by type")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Products were found and returned successfully",
          response = ProductResponse.class,
          responseContainer = "List"),
      @ApiResponse(
          code = 400,
          message = "Invalid request parameters",
          response = ResponseDTO.class)
  })
  @GetMapping("products/featured")
  public ResponseEntity<ResponseDTO<List<ProductResponse>>> getProductsByCategory(
      @RequestParam OfferingType type
  ) {

    return toResponseEntity(productServiceFacade.getFeaturedProductsBy(type));
  }

  @ApiOperation(value = "Fetch product reviews against the product (slug)")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Product reviews were successfully fetched",
          response = ProductReviewResponse.class,
          responseContainer = "List"),
      @ApiResponse(
          code = 400,
          message = "Product reviews could not be fetched due to bad request body or parameters",
          response = ResponseDTO.class)
  })
  @GetMapping("review/{productSlug}")
  public ResponseEntity<ResponseDTO<List<ProductReviewResponse>>> getProductReviews(
      @PathVariable String productSlug,
      @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
      @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

    return toResponseEntity(productServiceFacade.getProductReviews(productSlug, pageNumber, pageSize));
  }
}
