package com.strabl.service.product.service;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.domain.entity.Category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryService {
	Page<CategoryDTO> fetchCategoriesBy(OfferingType type, PagedResponseRequest pagedResponseRequest);

	Page<ProductResponse> getByFilterCategory(boolean isRental, boolean isTbyb, PagedResponseRequest pagedResponseRequest);

	Category addCategory(CategoryDTO categoryDTO, Integer userId);
}
