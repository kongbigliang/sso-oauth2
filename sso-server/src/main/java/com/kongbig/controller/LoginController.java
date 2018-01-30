package com.kongbig.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kongbig.model.Client;
import com.kongbig.model.Code;
import com.kongbig.model.OAuthUser;
import com.kongbig.model.ResultModel;
import com.kongbig.model.ShopUser;
import com.kongbig.model.TempTokenModel;
import com.kongbig.security.SecurityContext;
import com.kongbig.service.ICodeService;
import com.kongbig.service.ILoginService;
import com.kongbig.service.IOauthUserService;
import com.kongbig.service.IShopUserService;
import com.kongbig.service.ITempTokenService;
import com.kongbig.util.StringUtilsss;
import com.kongbig.util.UUIDUtil;
import com.kongbig.util.UrlUtil;

/**
 * 
 * @author kongbig
 *
 */
@Controller
@RequestMapping("/LoginController")
public class LoginController {

	@Resource
	private IOauthUserService oauthUserServiceImpl;
	@Resource
	private IShopUserService shopUserServiceImpl;
	@Resource
	private ICodeService codeServiceImpl;
	@Resource
	private ILoginService loginServiceImpl;
	@Resource
	private ITempTokenService tempTokenServiceImpl;

	/**
	 * sso-server管理人员登陆
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 用户在这里已经查出来
		OAuthUser oAuthUser = oauthUserServiceImpl.getUserModel(username, password);
		if (oAuthUser != null) {
			SecurityContext.setCurrentOAuthUser(oAuthUser);
			response.sendRedirect(request.getContextPath() + "/portal/default/index.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/common/login.jsp");
		}
	}

	/**
	 * 提供登陆页面
	 * 
	 * @param request
	 * @param response
	 * @param client
	 * @param redirectURI
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/offerLoginPage.do")
	public void offerLoginPage(HttpServletRequest request, HttpServletResponse response, Client client,
			String redirectURI, String returnURI) throws ServletException, IOException {

		String deRedirectURI = null;
		if (redirectURI != null) {
			deRedirectURI = UrlUtil.getURLEncoderString(redirectURI);
		}
		String deReturnURI = null;
		if (returnURI != null) {
			deReturnURI = UrlUtil.getURLEncoderString(returnURI);
		}

		ResultModel result1 = loginServiceImpl.check(client, request.getServerName(), redirectURI);
		/*
		 * clientID、TempToken、从redirectURI取出的ip、目标地址returnURI
		 */
		TempTokenModel tt = new TempTokenModel(UUIDUtil.getUUID(), client.getClientID(),
				StringUtilsss.getIP(redirectURI), returnURI);
		ResultModel result2 = tempTokenServiceImpl.addTempToken(tt);

		if (result1.getCode().equals("001") && result2.getCode().equals("001")) {
			response.sendRedirect("/sso-server/common/userlogin.jsp?redirectURI=" + deRedirectURI + "&returnURI="
					+ deReturnURI + "&tempToken=" + tt.getTempToken());
		} else {
			response.sendRedirect("/sso-server/common/errorRequest.jsp");
		}
	}

	/**
	 * 电子商城用户登陆
	 * 
	 * @param username
	 * @param password
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/shopuserlogin.do")
	public void userlogin(HttpServletResponse response, String account, String password, String redirectURI,
			String returnURI, String tempToken) throws ServletException, IOException {

		String deRedirectURI = null;
		if (redirectURI != null) {
			deRedirectURI = UrlUtil.getURLDecoderString(redirectURI);
		}
		String deReturnURI = null;
		if (returnURI != null) {
			deReturnURI = UrlUtil.getURLDecoderString(returnURI);
		}

		ResultModel result = null;
		/*
		 * 查询TempToken对象,比对重定向URI的ip
		 */
		result = tempTokenServiceImpl.checkReURI(tempToken, deRedirectURI, deReturnURI);
		if (result != null && result.getCode().equals("000")) {
			response.sendRedirect("/sso-server/common/errorRequest.jsp");
			return;
		}

		ShopUser shopUser = shopUserServiceImpl.getUserModel(account, password);

		if (shopUser != null && shopUser.getId() != null) {
			System.out.println(shopUser);
			Code code = new Code();
			code.setCode(UUIDUtil.getUUID());
			code.setCreateTime(new Date());
			code.setExpiresIn("180");// 授权码的生存时间
			code.setAccountFk(account);
			codeServiceImpl.addCode(code);// 添加到数据库
			response.sendRedirect(deRedirectURI + "?code=" + code.getCode() + "&account=" + code.getAccountFk()
					+ "&returnURI=" + returnURI);
		} else {
			response.sendRedirect("/sso-server/common/userlogin.jsp?redirectURI=" + deRedirectURI + "&returnURI="
					+ deReturnURI + "&tempToken=" + tempToken);
		}

	}

}
