package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.criteria.page.StrablPageable;
import com.strabl.sdk.domain.criteria.specification.CategorySpecification;
import com.strabl.sdk.domain.dao.CategoryDao;
import com.strabl.sdk.domain.dao.ProductDao;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.mapper.CategoryMapper;
import com.strabl.sdk.domain.mapper.ProductMapper;
import com.strabl.sdk.domain.repository.CategoryRepository;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao {

  private final CategoryRepository categoryRepository;
  private final ProductDao productDao;
  private final UserDao userDao;

	  @Override
	  public Page<Category> getByOfferingType(OfferingType offeringType, PagedResponseRequest pagedResponseRequest) {

		Specification<Category> specification = CategorySpecification.of(offeringType);
		Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);

		return categoryRepository.findAll(specification, pageable);
	  }

	  @Override
	  public Page<Category> getAllCategory(PagedResponseRequest pagedResponseRequest) {
		  Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);
		  return categoryRepository.getAllCategory(pageable);
	  }

	@Override
	public Page<ProductResponse> getByFilterCategory(boolean isRental, boolean isTbyb, PagedResponseRequest pagedResponseRequest) {
		Pageable pageable = StrablPageable.createPageWithSort(pagedResponseRequest);
		List<List<Product>> listOfAllProduct = new ArrayList<>();
		List<ProductResponse> productListResponse = new ArrayList<>();

		Specification<Category> specification = CategorySpecification.getByFilterCategory(isRental, isTbyb);

		List<Category> categoryList = categoryRepository.findAll(specification);

		for (Category c : categoryList) {
			listOfAllProduct.add(productDao.getAllProductByCategory(c.getName()));
		}

		// loop though listOfAllProduct and then loop each product in productList inside it
		for (int i = 0; i < listOfAllProduct.size(); i++) {
			for (Product p : listOfAllProduct.get(i)) {
				productListResponse.add(ProductMapper.toProductResponse(p));
			}
		}
		if (productListResponse.isEmpty()){
			return new PageImpl<>(productListResponse, pageable, productListResponse.size());
		}
		return new PageImpl<>(productListResponse.subList(pageable.getPageNumber(),
				(pageable.getPageSize() > productListResponse.size() ? productListResponse.size() : pagedResponseRequest.getPageSize())), pageable, productListResponse.size());
	}

	@Override
	public Category addCategory(Category category, Integer userId) {
		User user = userDao.findByIdOrThrow(userId);
		if (!user.getIsEnabled()) {
			throw StrablException.of(ResponseType.USER_NOT_VERIFIED_OR_ENABLED);
		}
		category.setCreatedBy(user);
		category.setCreatedAt(Instant.now());
		category.setUpdatedBy(user);
		return categoryRepository.save(category);
	}
}
