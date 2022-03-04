package com.strabl.service.product.api;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.ProductReviewRequest;
import com.strabl.sdk.common.dto.response.ProductReviewResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.service.product.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/review")
@AllArgsConstructor
public class ReviewController {

  private static final Integer MAX_PAGE_SIZE = 25;

  private final ReviewService reviewService;

  @PostMapping("product/{productSlug}")
  public ResponseDTO<ProductReviewResponse> addReviewAgainstProduct(
      @PathVariable String productSlug,
      @RequestParam Integer reviewerUserId,
      @RequestBody ProductReviewRequest productReviewRequest) {

    return ResponseDTO.success(reviewService.addReview(productSlug, reviewerUserId, productReviewRequest));
  }

  @GetMapping("product/{productSlug}")
  public ResponseDTO<List<ProductReviewResponse>> getReviewsAgainstProduct(
      @PathVariable String productSlug,
      @RequestParam Integer pageNumber,
      @RequestParam Integer pageSize
  ) {

    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(reviewService.getReviewsBy(productSlug, pagedResponseRequest));
  }

  @GetMapping("reviews/unapproved")
  public ResponseDTO<List<ProductReviewResponse>> getUnapprovedReviews(
      @RequestParam Integer pageNumber,
      @RequestParam Integer pageSize
  ) {

    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(reviewService.getUnapprovedReviews(pagedResponseRequest));
  }

  @PatchMapping("{reviewId}/approve")
  public ResponseDTO<ProductReviewResponse> approveProductReview(
      @PathVariable Integer reviewId
  ) {

    return ResponseDTO.success(reviewService.updateApprovalStatus(reviewId, true));
  }

  @PatchMapping("{reviewId}/disapprove")
  public ResponseDTO<ProductReviewResponse> disapproveProductReview(
      @PathVariable Integer reviewId
  ) {

    return ResponseDTO.success(reviewService.updateApprovalStatus(reviewId, false));
  }

}
