package com.strabl.service.seller.serviceimpl;

import org.springframework.stereotype.Service;

import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.StrablException;
import com.strabl.sdk.domain.dao.ProductDao;
import com.strabl.sdk.domain.dao.UserDao;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import com.strabl.service.seller.service.SellerService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService{

	private  UserDao userDao;
	private ProductDao productDao;
		
		@Override
		public String becomeSeller(Integer id) {
			User user = userDao.findByIdOrThrow(id);
			user.setIsseller(true);
		userDao.update(user);
		return "Customer" + user.getFullName() + "Successfully become Seller";
		}

		@Override
		public void changeStatus(Integer id, UserStatus inactive) {
		   User user = userDao.findByIdOrThrow(id);
		   if(!user.getIsseller()){
		      throw StrablException.of(ResponseType.BAD_REQUEST);
		   }
		   userDao.update(user);

		}
}
