package com.kongbig.dao;

import org.springframework.stereotype.Repository;

import com.kongbig.model.ShopUser;

@Repository
public interface ShopUserDao {
	
	public ShopUser getUserModel(String account, String password);

}
