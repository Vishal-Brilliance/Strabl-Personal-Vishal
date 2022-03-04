package com.strabl.service.admin.service;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.TransactionDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.entity.User;
import org.springframework.data.domain.Page;

public interface AdminService {

	Page<UserResponseDTO> getAllCustomers( PagedResponseRequest pagedResponseRequest );
	Page<Category> getAllCategory(PagedResponseRequest pagedResponseRequest);
	
}
