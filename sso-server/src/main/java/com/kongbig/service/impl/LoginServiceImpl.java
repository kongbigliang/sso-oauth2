package com.kongbig.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongbig.dao.ClientDao;
import com.kongbig.model.Client;
import com.kongbig.model.ResultModel;
import com.kongbig.service.ILoginService;
import com.kongbig.util.StringUtilsss;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ClientDao clientDao;

	@Override
	public ResultModel check(Client client, String reIP, String redirectURI) {
		ResultModel result = null;
		Client clientTemp = clientDao.getClientModel(client.getClientID(), client.getClientSecret());
		if (clientTemp != null && !StringUtils.isEmpty(clientTemp.getClientID())) {
			// 请求ip和redirectURI的ip都校验
			if (clientTemp.getIp().equals(reIP) && clientTemp.getIp().equals(StringUtilsss.getIP(redirectURI))) {
				result = new ResultModel("001", "success");
				return result;
			}
		}
		return new ResultModel("000", "fail");
	}

}
