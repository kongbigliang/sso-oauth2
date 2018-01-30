package com.kongbig.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kongbig.model.AccessTokenModel;
import com.kongbig.model.Code;
import com.kongbig.model.RefreshTokenModel;
import com.kongbig.model.ResultModel;
import com.kongbig.service.IAccessTokenService;
import com.kongbig.service.ICodeService;
import com.kongbig.service.IRefreshTokenService;
import com.kongbig.util.TimeUtils;
import com.kongbig.util.UUIDUtil;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/oauth/authorize")
public class AuthorizeController {

	@Resource
	private ICodeService codeServiceImpl;
	@Resource
	private IRefreshTokenService refreshTokenServiceImpl;
	@Resource
	private IAccessTokenService accessTokenServiceImpl;

	@RequestMapping(value = "/checkCode.do")
	public void checkCode(HttpServletResponse response, String code, String account, String returnURI)
			throws ServletException, IOException {

		// 查询数据库并校验code
		Code codeModel = codeServiceImpl.getCodeByCId(code);

		ResultModel result = null;
		// codeModel不为空,账号一致,且没过期.
		if (codeModel != null && codeModel.getAccountFk().equals(account)
				&& !TimeUtils.isOutOfDate(codeModel.getCreateTime(), codeModel.getExpiresIn())) {
			// 生成token
			AccessTokenModel token = new AccessTokenModel(UUIDUtil.getUUID(), new Date(), "1800", UUIDUtil.getUUID(),
					account);
			RefreshTokenModel reToken = new RefreshTokenModel(token.getRefreshToken(), token.getCreateTime(), "86400");// oneday

			// 将token插入数据库
			if (refreshTokenServiceImpl.addRefreshToken(reToken) && accessTokenServiceImpl.addAccessToken(token)) {
				result = new ResultModel("201", returnURI, token);
				result.sendData(response);
			} else {
				result = new ResultModel("502", "operate failing");
				result.sendData(response);
			}
		} else {
			result = new ResultModel("501", "code is error");
			result.sendData(response);
		}
	}

}
