package com.kongbig.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.kongbig.model.AccessTokenModel;
import com.kongbig.model.ResultModel;
import com.kongbig.util.CookieUtils;
import com.kongbig.util.HttpClientUtil;
import com.kongbig.util.JsonTools;
import com.kongbig.util.PropertiesUtil;

/**
 * 公开接口- http://127.0.0.1:8080/sso-client/sso/oauth
 * 
 * @author kongbig
 *
 */
@SuppressWarnings("serial")
public abstract class OAuthServlet extends HttpServlet {

	// 从配置文件中获取sso-server的ip和端口号
	private String SIP = PropertiesUtil.getValue("SSO_SERVER_IP");
	private String SPORT = PropertiesUtil.getValue("SSO_SERVER_PORT");
	private String clientID = PropertiesUtil.getValue("CLIENT$ID");
	private String clientSecret = PropertiesUtil.getValue("CLIENT$SECRET");
	private String redirectURI = PropertiesUtil.getValue("SSO_CLIENT_URI");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		hasLegalToken(req, resp);
	}

	/**
	 * 成功后的回调方法
	 * 
	 * @param req
	 * @param resp
	 * @param tokenNew
	 */
	public abstract void loginSuccess(HttpServletRequest req, HttpServletResponse resp, AccessTokenModel tokenNew);

	/**
	 * 失败后的回调方法
	 * 
	 * @param req
	 * @param resp
	 */
	public abstract void loginError(HttpServletRequest req, HttpServletResponse resp);

	/**
	 * 看有无合法的accessToken
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void hasLegalToken(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// returnURI
		String returnURI = request.getParameter("returnURI");

		/*
		 * 从浏览器中获取cookie
		 */
		String accessToken = CookieUtils.getCookieValue(request, "accessToken");
		String refreshToken = CookieUtils.getCookieValue(request, "refreshToken");

		/*
		 * 是否有授权码?,有,HttpCLient到AS校验->成功!直接拿到Token
		 */
		/*
		 * 1.addCookie 2.result.sendData(response); 3.return;
		 */
		String code = request.getParameter("code");
		String account = request.getParameter("account");

		/*
		 * 登陆的情况,有code.
		 */
		if (!StringUtils.isEmpty(code)) {
			String resultStr = HttpClientUtil.doGet("http://" + SIP + ":" + SPORT
					+ "/sso-server/oauth/authorize/checkCode.do?code=" + code + "&account=" + account);
			ResultModel result = new Gson().fromJson(resultStr, ResultModel.class);

			if (result.getCode().equals("201")) {
				AccessTokenModel tokenNew = new Gson().fromJson(JsonTools.ObjToJSON(result.getData()),
						AccessTokenModel.class);

				// 登陆成功,设置cookie.(Integer.parseInt(tokenNew.getExpiresIn())==1800)
				CookieUtils.setCookieValue(response, "/", 1800, tokenNew);

				loginSuccess(request, response, tokenNew);

				request.getRequestDispatcher(returnURI + "?account=" + tokenNew.getAccount()).forward(request,
						response);

				// 结束方法
				return;
			} else if (result.getCode().equals("502")) {
				loginError(request, response);
				return;
			} else {
				loginError(request, response);
				return;
			}
		}

		if (!StringUtils.isEmpty(accessToken)) {// 有aToken,就不用rToken
			AccessTokenModel accessTokenModel = new Gson().fromJson(accessToken, AccessTokenModel.class);
			StringBuilder sb = new StringBuilder();
			sb = getTokenUri(accessTokenModel);

			String resultStr = HttpClientUtil
					.doGet("http://" + SIP + ":" + SPORT + "/sso-server/oauth/token/checkAccessToken.do?clientID="
							+ clientID + "&clientSecret=" + clientSecret + sb.toString());

			ResultModel result = new Gson().fromJson(resultStr, ResultModel.class);

			AccessTokenModel tokenReset = new Gson().fromJson(JsonTools.ObjToJSON(result.getData()),
					AccessTokenModel.class);

			if (result.getCode().equals("201")) {
				request.setAttribute("resultStr", resultStr);// 让客户端拿

				// 重新更新浏览器的两个token
				CookieUtils.setCookieValue(response, "/", 1800, tokenReset);

				loginSuccess(request, response, tokenReset);

				request.getRequestDispatcher(returnURI + "?account=" + tokenReset.getAccount()).forward(request,
						response);

			}

		} else if (accessToken == null && refreshToken != null) {// 只有rToken
			AccessTokenModel accessTokenModel = new Gson().fromJson(refreshToken, AccessTokenModel.class);

			StringBuilder sb = new StringBuilder();
			sb = getTokenUri(accessTokenModel);

			String resultStr = HttpClientUtil
					.doGet("http://" + SIP + ":" + SPORT + "/sso-server/oauth/token/checkAccessToken.do?clientID="
							+ clientID + "&clientSecret=" + clientSecret + sb.toString());

			ResultModel result = new Gson().fromJson(resultStr, ResultModel.class);

			AccessTokenModel tokenReset = new Gson().fromJson(JsonTools.ObjToJSON(result.getData()),
					AccessTokenModel.class);

			if (result.getCode().equals("201")) {
				request.setAttribute("resultStr", resultStr);// 让客户端拿

				// 重新更新浏览器的两个token
				CookieUtils.setCookieValue(response, "/", 1800, tokenReset);

				loginSuccess(request, response, tokenReset);

				request.getRequestDispatcher(returnURI + "?account=" + tokenReset.getAccount()).forward(request,
						response);
			}

		} else {
			// 无token,转去userlogin.jsp
			response.sendRedirect("http://" + SIP + ":" + SPORT
					+ "/sso-server/LoginController/offerLoginPage.do?redirectURI=" + redirectURI + "&clientID="
					+ clientID + "&clientSecret=" + clientSecret + "&returnURI=" + returnURI);
		}
	}

	private StringBuilder getTokenUri(AccessTokenModel accessTokenModel) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isEmpty(accessTokenModel.getAccessToken())) {
			sb.append("&accessToken=" + accessTokenModel.getAccessToken());
		}
		if (!StringUtils.isEmpty(accessTokenModel.getRefreshToken())) {
			sb.append("&refreshToken=" + accessTokenModel.getRefreshToken());
		}
		if (!StringUtils.isEmpty(accessTokenModel.getAccount())) {
			sb.append("&account=" + accessTokenModel.getAccount());
		}
		return sb;
	}

}
