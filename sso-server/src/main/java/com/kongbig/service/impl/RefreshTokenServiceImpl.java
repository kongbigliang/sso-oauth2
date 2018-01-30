package com.kongbig.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongbig.dao.RefreshTokenDao;
import com.kongbig.model.RefreshTokenModel;
import com.kongbig.service.IRefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {
	
	@Autowired
	private RefreshTokenDao refreshTokenDao;

	@Override
	public boolean addRefreshToken(RefreshTokenModel reToken) {
		return refreshTokenDao.addRefreshToken(reToken);
	}

	@Override
	public List<RefreshTokenModel> getAllrefreshToken() {
		return refreshTokenDao.getAllrefreshToken();
	}

	@Override
	public void deleteRefreshToken(String refreshToken) {
		refreshTokenDao.deleteRefreshToken(refreshToken);
	}

}
