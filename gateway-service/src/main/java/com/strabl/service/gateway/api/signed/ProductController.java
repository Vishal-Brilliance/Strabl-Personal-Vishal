package com.strabl.service.gateway.api.signed;

import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.request.ProductReviewRequest;
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

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/api/product")
@AllArgsConstructor
@Api(
    tags = {"Product Controller"},
    value = "Allows you to add/edit/remove products as a signed in user")
public class ProductController extends BaseController {

  private final ProductServiceFacade productServiceFacade;

  @ApiOperation(value = "Creates a new product entry on the platform")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Product was successfully created",
          response = ProductResponse.class),
      @ApiResponse(
          code = 400,
          message = "Product was not created due to bad request body or parameters",
          response = ResponseDTO.class)
  })
  @PostMapping("new")
  public ResponseEntity<ResponseDTO<ProductResponse>> newProduct(
      @Valid @RequestBody CreateProductRequest createProductRequest) {

    return toResponseEntity(productServiceFacade.newProduct(createProductRequest));
  }

  @ApiOperation(value = "Add a new product review against the product (slug)")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Product review was successfully created",
          response = ProductReviewResponse.class),
      @ApiResponse(
          code = 400,
          message = "Product review was not created due to bad request body or parameters",
          response = ResponseDTO.class)
  })
  @PostMapping("review/{productSlug}")
  public ResponseEntity<ResponseDTO<ProductReviewResponse>> addProductReview(
      @PathVariable String productSlug,
      @Valid @RequestBody ProductReviewRequest productReviewRequest) {

    return toResponseEntity(productServiceFacade.addProductReview(productSlug, productReviewRequest));
  }

  @ApiOperation(value = "Get products that are pending approval")
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
  @GetMapping("products/unapproved")
  public ResponseEntity<ResponseDTO<List<ProductResponse>>> getUnapprovedProducts(
      @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
      @RequestParam(required = false, defaultValue = "10") Integer pageSize
  ) {

    return toResponseEntity(productServiceFacade.getUnapprovedProducts(pageNumber, pageSize));
  }

  @ApiOperation(value = "Approve a product")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Product was successfully approved",
          response = ProductResponse.class),
      @ApiResponse(
          code = 400,
          message = "Invalid request parameters",
          response = ResponseDTO.class)
  })
  @PatchMapping("{productId}/approve")
  public ResponseEntity<ResponseDTO<ProductResponse>> approveProduct(
      @PathVariable Integer productId
  ) {

    return toResponseEntity(productServiceFacade.setProductApprovalStatus(productId, true));
  }

  @ApiOperation(value = "Disapprove a product")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Product was successfully disapproved",
          response = ProductResponse.class),
      @ApiResponse(
          code = 400,
          message = "Invalid request parameters",
          response = ResponseDTO.class)
  })
  @PatchMapping("{productId}/disapprove")
  public ResponseEntity<ResponseDTO<ProductResponse>> disapproveProduct(
      @PathVariable Integer productId
  ) {

    return toResponseEntity(productServiceFacade.setProductApprovalStatus(productId, false));
  }

  @ApiOperation(value = "Get product reviews that are pending approval")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Product reviews were found and returned successfully",
          response = ProductReviewResponse.class,
          responseContainer = "List"),
      @ApiResponse(
          code = 400,
          message = "Invalid request parameters",
          response = ResponseDTO.class)
  })
  @GetMapping("reviews/unapproved")
  public ResponseEntity<ResponseDTO<List<ProductReviewResponse>>> getUnapprovedProductReviews(
      @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
      @RequestParam(required = false, defaultValue = "10") Integer pageSize
  ) {

    return toResponseEntity(productServiceFacade.getUnapprovedProductReviews(pageNumber, pageSize));
  }

  @ApiOperation(value = "Approve a product review")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Product review was successfully approved",
          response = ProductReviewResponse.class),
      @ApiResponse(
          code = 400,
          message = "Invalid request parameters",
          response = ResponseDTO.class)
  })
  @PatchMapping("review/{reviewId}/approve")
  public ResponseEntity<ResponseDTO<ProductReviewResponse>> approveProductReview(
      @PathVariable Integer reviewId
  ) {

    return toResponseEntity(productServiceFacade.setProductReviewApprovalStatus(reviewId, true));
  }

  @ApiOperation(value = "Disapprove a product review")
  @ApiResponses({
      @ApiResponse(
          code = 200,
          message = "Product review was successfully disapproved",
          response = ProductReviewResponse.class),
      @ApiResponse(
          code = 400,
          message = "Invalid request parameters",
          response = ResponseDTO.class)
  })
  @PatchMapping("review/{reviewId}/disapprove")
  public ResponseEntity<ResponseDTO<ProductReviewResponse>> disapproveProductReview(
      @PathVariable Integer reviewId
  ) {

    return toResponseEntity(productServiceFacade.setProductReviewApprovalStatus(reviewId, false));
  }
}
