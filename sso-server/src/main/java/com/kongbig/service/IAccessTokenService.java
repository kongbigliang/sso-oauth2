package com.kongbig.service;

import com.kongbig.model.AccessTokenModel;
import com.kongbig.model.Client;
import com.kongbig.model.ResultModel;

public interface IAccessTokenService {

	boolean addAccessToken(AccessTokenModel token);

	ResultModel checkAccessToken(Client client, AccessTokenModel accessToken);

	void deleteTokenByRtoken(String refreshToken);

}
