package com.strabl.service.product.service;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface SearchService {
  Page<ProductResponse> searchByTagsAndTitle(String searchTerm, PagedResponseRequest pagedResponseRequest);
}
