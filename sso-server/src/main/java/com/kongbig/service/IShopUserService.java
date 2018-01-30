package com.kongbig.service;

import com.kongbig.model.ShopUser;

public interface IShopUserService {
	
	public ShopUser getUserModel(String account, String password);

}
