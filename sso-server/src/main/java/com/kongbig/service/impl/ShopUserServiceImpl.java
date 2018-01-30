package com.kongbig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongbig.dao.ShopUserDao;
import com.kongbig.model.ShopUser;
import com.kongbig.service.IShopUserService;

@Service
public class ShopUserServiceImpl implements IShopUserService {
	
	@Autowired
	private ShopUserDao shopUserDao;

	@Override
	public ShopUser getUserModel(String account, String password) {
		return shopUserDao.getUserModel(account, password);
	}

}
