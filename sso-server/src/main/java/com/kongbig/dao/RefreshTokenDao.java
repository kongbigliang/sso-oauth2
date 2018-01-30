package com.kongbig.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kongbig.model.RefreshTokenModel;

@Repository
public interface RefreshTokenDao {

	boolean addRefreshToken(RefreshTokenModel reToken);

	RefreshTokenModel getRefreshTokenByPK(String refreshToken);

	boolean updateRefreshToken(RefreshTokenModel rt);

	List<RefreshTokenModel> getAllrefreshToken();
	
	void deleteRefreshToken(String refreshToken);

}
