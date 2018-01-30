package com.kongbig.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kongbig.model.Client;
import com.kongbig.model.ResultModel;
import com.kongbig.service.IClientService;

@Controller
@RequestMapping("/oauth/client")
public class ClientController {

	@Resource
	private IClientService clientServiceImpl;

	@RequestMapping("/deleteClient.do")
	@ResponseBody
	public ResultModel deleteOAuthUser(String ids) throws ServletException, IOException {
		String[] idsArg = ids.split(",");
		ResultModel result = clientServiceImpl.deleteClient(idsArg);
		return result;
	}

	@RequestMapping(value = "/updateStatus.do", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateStatus(String clientID, String status) throws ServletException, IOException {
		ResultModel result = clientServiceImpl.updateStatus(clientID, status);
		return result;
	}
	
	@RequestMapping("/common/addClient.do")
	@ResponseBody
	public ResultModel addClient(String clientMessage) throws ServletException, IOException {
		Gson gson = new Gson();
		Client client = gson.fromJson(clientMessage, Client.class);
		ResultModel result = null;
		result = clientServiceImpl.addClient(client);
		return result;
	}

	@RequestMapping("/common/checkClientname.do")
	public void checkUsername(String clientName, HttpServletResponse response) throws ServletException, IOException {
		Client client = clientServiceImpl.getClientByName(clientName);
		if (client != null && client.getClientID() != null) {
			response.getWriter().write("clientName_exist");
		}
	}

	@RequestMapping("/getClientById.do")
	public void getClientById(HttpServletRequest request, HttpServletResponse response, String id)
			throws ServletException, IOException {
		Client client = clientServiceImpl.getClientById(id);
		request.setAttribute("client", client);
		request.getRequestDispatcher("/module/client/client-edit.jsp").forward(request, response);
	}

}
