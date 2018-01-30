package com.kongbig.service;

import com.kongbig.model.OAuthUser;
import com.kongbig.model.ResultModel;

public interface IOauthUserService {

	public OAuthUser getUserModel(String username, String password);

	public OAuthUser getOAuthUserByName(String username);

	public OAuthUser getOAuthUserById(String id);
	
	public ResultModel addOAuthUser(OAuthUser oAuthUser);

	public ResultModel updateOAuthUser(OAuthUser oAuthUser);

	public ResultModel deleteOAuthUser(String[] ids);
	
}
