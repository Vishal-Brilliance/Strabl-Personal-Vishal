package com.strabl.sdk.domain.dao;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.domain.entity.Category;

import java.util.List;

import org.springframework.data.domain.Page;

public interface CategoryDao {

    Page<Category> getByOfferingType(OfferingType offeringType, PagedResponseRequest pagedResponseRequest);

	Page<Category> getAllCategory(PagedResponseRequest pagedResponseRequest);

    Page<ProductResponse> getByFilterCategory(boolean isRental, boolean isTbyb, PagedResponseRequest pagedResponseRequest);

    Category addCategory(Category category, Integer userId);
}
