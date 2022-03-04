package com.strabl.service.product.api;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.service.product.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/search")
@AllArgsConstructor
public class SearchController {

  private static final Integer MAX_PAGE_SIZE = 25;

  private final SearchService searchService;

  @GetMapping("products")
  public ResponseDTO<List<ProductResponse>> searchByTagsAndTitle(
      @RequestParam String q,
      @RequestParam Integer pageNumber,
      @RequestParam Integer pageSize
  ) {

    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(searchService.searchByTagsAndTitle(q, pagedResponseRequest));
  }
}
