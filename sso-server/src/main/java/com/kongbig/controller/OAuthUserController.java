package com.kongbig.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kongbig.model.OAuthUser;
import com.kongbig.model.ResultModel;
import com.kongbig.service.IOauthUserService;

/**
 * 
 * @author kongbig
 *
 */
@Controller
@RequestMapping("/oauthuser")
public class OAuthUserController {

	@Resource
	private IOauthUserService oauthUserServiceImpl;

	@RequestMapping("/addOAuthUser.do")
	@ResponseBody
	public ResultModel addOAuthUser(String jsonStr) throws ServletException, IOException {
		Gson gson = new Gson();
		OAuthUser oAuthUser = gson.fromJson(jsonStr, OAuthUser.class);
		ResultModel result = null;
		result = oauthUserServiceImpl.addOAuthUser(oAuthUser);
		return result;
	}

	@RequestMapping("/updateOAuthUser.do")
	@ResponseBody
	public ResultModel updateOAuthUser(String jsonStr) throws ServletException, IOException {
		Gson gson = new Gson();
		OAuthUser oAuthUser = gson.fromJson(jsonStr, OAuthUser.class);
		ResultModel result = null;
		result = oauthUserServiceImpl.updateOAuthUser(oAuthUser);
		return result;
	}

	@RequestMapping("/deleteOAuthUser.do")
	@ResponseBody
	public ResultModel deleteOAuthUser(String ids) throws ServletException, IOException {
		String[] idsArg = ids.split(",");
		ResultModel result = oauthUserServiceImpl.deleteOAuthUser(idsArg);
		return result;
	}

	@RequestMapping("/checkUsername.do")
	public void checkUsername(String username, HttpServletResponse response) throws ServletException, IOException {
		OAuthUser oAuthUser = oauthUserServiceImpl.getOAuthUserByName(username);
		if (oAuthUser != null && oAuthUser.getId() != null) {
			response.getWriter().write("username_exist");
		}
	}

	@RequestMapping("/getOAuthUserById.do")
	public void getOAuthUserById(HttpServletRequest request, HttpServletResponse response, String id)
			throws ServletException, IOException {
		OAuthUser oAuthUser = oauthUserServiceImpl.getOAuthUserById(id);
		request.setAttribute("oAuthUser", oAuthUser);
		request.getRequestDispatcher("/module/user/user-edit.jsp").forward(request, response);
	}

}
