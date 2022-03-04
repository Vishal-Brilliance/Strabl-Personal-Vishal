package com.strabl.service.admin.service.impl;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.TransactionDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.domain.dao.CategoryDao;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.mapper.UserMapper;
import com.strabl.service.admin.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final UserDao userDao;
	private final CategoryDao categoryDao;
	
	@Override
	public Page<UserResponseDTO> getAllCustomers(PagedResponseRequest pagedResponseRequest) {
		return userDao.getAllCustomers(pagedResponseRequest)
				.map(UserMapper::toUserResponseDTO);
	}
	
	@Override
	public Page<Category> getAllCategory(PagedResponseRequest pagedResponseRequest) {
		return categoryDao.getAllCategory(pagedResponseRequest);
	}

}
