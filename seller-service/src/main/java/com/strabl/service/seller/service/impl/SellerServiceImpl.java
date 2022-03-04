package com.strabl.service.seller.service.impl;

import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.request.CreateProductRequest;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.dao.ProductDao;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.mapper.ProductMapper;
import com.strabl.service.seller.service.SellerService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService{

	private final UserDao userDao;
	private final ProductDao productDao;
		
		@Override
		public String  becomeSeller(Integer id) {
			User user = userDao.findByIdOrThrow(id);
			user.setIsseller(true);
			user.setId(id);      // user become seller with the same id
		    userDao.update(user);
		return " Customer " + user.getFullName() + " Successfully become Seller ";
		}

	@Override
	public void changeStatus(Integer id, UserStatus inactive) {
		User user = userDao.findByIdOrThrow(id);
		// check for isSeller
		if(!user.getIsseller()){
			throw StrablException.of(ResponseType.BAD_REQUEST);
		}
		userDao.update(user);

	}

	@Override
	public Page<ProductResponse> sellerProduct(Integer sellerId, PagedResponseRequest pagedResponseRequest) {
		return productDao.sellerProduct(sellerId, pagedResponseRequest)
				.map(ProductMapper::toProductResponse);
	}


}
