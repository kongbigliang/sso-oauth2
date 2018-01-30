package com.kongbig.dao;

import org.springframework.stereotype.Repository;

import com.kongbig.model.AccessTokenModel;

@Repository
public interface AccessTokenDao {

	boolean addAccessToken(AccessTokenModel token);

	AccessTokenModel getAccessTokenByPK(String accessToken);

	boolean updateAccessToken(AccessTokenModel at);

	AccessTokenModel getAccessTokenByFK(String refreshToken);

	void deleteTokenByRtoken(String refreshToken);

}
