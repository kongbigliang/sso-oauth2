package com.kongbig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongbig.dao.OAuthUserDao;
import com.kongbig.model.OAuthUser;
import com.kongbig.model.ResultModel;
import com.kongbig.security.SecurityContext;
import com.kongbig.service.IOauthUserService;
import com.kongbig.util.UUIDUtil;

@Service
public class OauthUserServiceImpl implements IOauthUserService {

	@Autowired
	private OAuthUserDao oAuthUserDao;

	@Override
	public OAuthUser getUserModel(String username, String password) {
		return oAuthUserDao.getUserModel(username, SecurityContext.encryptPassword(password));
	}

	@Override
	public OAuthUser getOAuthUserByName(String username) {
		return oAuthUserDao.getOAuthUserByName(username);
	}

	@Override
	public OAuthUser getOAuthUserById(String id) {
		return oAuthUserDao.getOAuthUserById(id);
	}

	@Override
	public ResultModel addOAuthUser(OAuthUser oAuthUser) {
		// 按用户名查询,有则添加失败
		OAuthUser oAuthUserTemp = oAuthUserDao.getOAuthUserByName(oAuthUser.getUsername());
		if (oAuthUserTemp != null && oAuthUserTemp.getId() != null) {
			return new ResultModel("002", "用户已存在,请重新输入!");
		}
		oAuthUser.setId(UUIDUtil.getUUID());
		oAuthUser.setPassword(SecurityContext.encryptPassword(oAuthUser.getUsername()));
		oAuthUser.setStatus("002");
		oAuthUser.setCreator(SecurityContext.getCurrentOAuthUser().getId());
		oAuthUser.setLastUpdate(SecurityContext.getCurrentOAuthUser().getId());
		if (!oAuthUserDao.addOAuthUser(oAuthUser)) {
			return new ResultModel("000", "新增用户失败！");
		}
		return new ResultModel("001", "新增用户成功！", oAuthUser);
	}

	@Override
	public ResultModel updateOAuthUser(OAuthUser oAuthUser) {
		oAuthUser.setLastUpdate(SecurityContext.getCurrentOAuthUser().getId());
		boolean result = oAuthUserDao.updateOAuthUser(oAuthUser);
		if (!result) {
			return new ResultModel("000", "修改用户失败！");
		}
		return new ResultModel("001", "修改用户成功！", oAuthUser);
	}

	@Override
	public ResultModel deleteOAuthUser(String[] ids) {
		if (oAuthUserDao.deleteOAuthUser(ids)) {
			return new ResultModel("001", "删除成功！");
		}
		return new ResultModel("000", "删除失败！");
	}

}
