package com.kongbig.service;

import java.util.List;

import com.kongbig.model.RefreshTokenModel;

public interface IRefreshTokenService {

	boolean addRefreshToken(RefreshTokenModel reToken);

	List<RefreshTokenModel> getAllrefreshToken();

	void deleteRefreshToken(String refreshToken);

}
