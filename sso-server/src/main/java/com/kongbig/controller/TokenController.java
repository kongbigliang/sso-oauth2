package com.kongbig.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kongbig.model.AccessTokenModel;
import com.kongbig.model.Client;
import com.kongbig.model.ResultModel;
import com.kongbig.service.IAccessTokenService;

/**
 * 校验token是否有效
 * 
 * @author kongbig
 *
 */
@Controller
@RequestMapping("/oauth/token")
public class TokenController {

	@Autowired
	private IAccessTokenService accessTokenServiceImpl;

	/**
	 * 有accessToken,校验accessToken.
	 * 
	 * @param request
	 * @param response
	 * @param clientID
	 * @param rediectURI
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/checkAccessToken.do")
	public void checkAccessToken(HttpServletResponse response, Client client, AccessTokenModel accessToken)
			throws ServletException, IOException {
		ResultModel result = null;
		result = accessTokenServiceImpl.checkAccessToken(client, accessToken);
		result.sendData(response);
	}

}
