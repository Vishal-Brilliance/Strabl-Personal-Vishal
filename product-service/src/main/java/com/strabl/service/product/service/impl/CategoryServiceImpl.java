package com.strabl.service.product.service.impl;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.dao.CategoryDao;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.mapper.CategoryMapper;
import com.strabl.service.product.service.CategoryService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryDao categoryDao;

  @Override
  public Page<CategoryDTO> fetchCategoriesBy(OfferingType type, PagedResponseRequest pagedResponseRequest) {

    return categoryDao.getByOfferingType(type, pagedResponseRequest)
        .map(CategoryMapper::toCategoryDTO);
  }

  @Override
  public Page<ProductResponse> getByFilterCategory(boolean isRental, boolean isTbyb, PagedResponseRequest pagedResponseRequest) {
    return categoryDao.getByFilterCategory(isRental, isTbyb, pagedResponseRequest);
  }

  @Override
  @Transactional
  public Category addCategory(CategoryDTO categoryDTO, Integer userId) {
    try {
      return categoryDao.addCategory(CategoryMapper.toCategoryEntityFrom(categoryDTO), userId);
    } catch (Exception e) {
      throw StrablException.of(ResponseType.BAD_REQUEST);
    }
  }
}
