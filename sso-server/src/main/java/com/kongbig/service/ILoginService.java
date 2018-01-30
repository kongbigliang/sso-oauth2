package com.kongbig.service;

import com.kongbig.model.Client;
import com.kongbig.model.ResultModel;

public interface ILoginService {

	/**
	 * 请求ip和redirectURI的ip都校验
	 * 
	 * @param client
	 * @param reIP
	 * @param redirectURI
	 * @return
	 */
	ResultModel check(Client client, String reIP, String redirectURI);

}
