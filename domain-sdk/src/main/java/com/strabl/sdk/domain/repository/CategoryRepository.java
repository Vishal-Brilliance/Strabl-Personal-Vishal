package com.strabl.sdk.domain.repository;

import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.domain.entity.Category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

	@Query(nativeQuery=true, value=" select* from category ")
	Page<Category> getAllCategory(Pageable pageable);




}
