package com.kongbig.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongbig.dao.AccessTokenDao;
import com.kongbig.dao.ClientDao;
import com.kongbig.dao.RefreshTokenDao;
import com.kongbig.model.AccessTokenModel;
import com.kongbig.model.Client;
import com.kongbig.model.RefreshTokenModel;
import com.kongbig.model.ResultModel;
import com.kongbig.service.IAccessTokenService;

@Service
public class AccessTokenServiceImpl implements IAccessTokenService {

	@Autowired
	private AccessTokenDao accessTokenDao;
	@Autowired
	private RefreshTokenDao refreshTokenDao;
	@Autowired
	private ClientDao clientDao;

	@Override
	public boolean addAccessToken(AccessTokenModel token) {
		return accessTokenDao.addAccessToken(token);
	}

	@Override
	public ResultModel checkAccessToken(Client client, AccessTokenModel accessToken) {
		ResultModel result = null;
		if (clientIsLegal(client)) {
			if (accessTokenIsLegal(accessToken)) {// token有效
				AccessTokenModel tokenVlid = getToken(accessToken);
				result = new ResultModel("201", "legal", tokenVlid);
			} else {
				result = new ResultModel("501", "pleaseLogin");
			}
		} else {
			result = new ResultModel("502", "errorRequest");
		}
		return result;
	}

	private AccessTokenModel getToken(AccessTokenModel accessToken) {
		if (accessToken != null && !StringUtils.isEmpty(accessToken.getAccessToken())) {
			AccessTokenModel at = accessTokenDao.getAccessTokenByPK(accessToken.getAccessToken());
			return at;
		} else if (accessToken != null && !StringUtils.isEmpty(accessToken.getRefreshToken())) {
			AccessTokenModel at = accessTokenDao.getAccessTokenByFK(accessToken.getRefreshToken());
			return at;
		} else {
			return null;
		}
	}

	private boolean accessTokenIsLegal(AccessTokenModel accessToken) {
		if (accessToken != null && !StringUtils.isEmpty(accessToken.getAccessToken())) {
			// 查询一下数据库有没有这个aToken就好
			AccessTokenModel at = accessTokenDao.getAccessTokenByPK(accessToken.getAccessToken());
			if (at != null && at.getAccount().equals(accessToken.getAccount())) {
				return true;
			} else {
				return false;
			}
		} else if (accessToken != null && !StringUtils.isEmpty(accessToken.getRefreshToken())) {
			// 查询一下数据库返回rToken所对应的aToken对象,重新设置时间,并设置回cookie
			AccessTokenModel at = accessTokenDao.getAccessTokenByFK(accessToken.getRefreshToken());
			RefreshTokenModel rt = refreshTokenDao.getRefreshTokenByPK(accessToken.getRefreshToken());
			if (at != null && at.getAccount().equals(accessToken.getAccount())) {
				at.setCreateTime(new Date());
				rt.setCreateTime(at.getCreateTime());
				accessTokenDao.updateAccessToken(at);
				refreshTokenDao.updateRefreshToken(rt);
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	private boolean clientIsLegal(Client client) {
		if (client != null && !StringUtils.isEmpty(client.getClientID())
				&& !StringUtils.isEmpty(client.getClientSecret())) {
			Client clientModel = clientDao.getClientModel(client.getClientID(), client.getClientSecret());
			if (clientModel != null && !StringUtils.isEmpty(clientModel.getClientID())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void deleteTokenByRtoken(String refreshToken) {
		accessTokenDao.deleteTokenByRtoken(refreshToken);
	}

}
