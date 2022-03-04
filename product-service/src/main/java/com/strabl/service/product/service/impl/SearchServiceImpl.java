package com.strabl.service.product.service.impl;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.domain.dao.ProductDao;
import com.strabl.sdk.domain.mapper.ProductMapper;
import com.strabl.service.product.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

  private final ProductDao productDao;

  @Override
  public Page<ProductResponse> searchByTagsAndTitle(String searchTerm, PagedResponseRequest pagedResponseRequest) {

    return productDao.searchByTagsAndTitle(searchTerm, pagedResponseRequest)
        .map(ProductMapper::toProductResponse);
  }
}
