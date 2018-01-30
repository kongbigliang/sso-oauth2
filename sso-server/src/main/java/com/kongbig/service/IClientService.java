package com.kongbig.service;

import com.kongbig.model.Client;
import com.kongbig.model.ResultModel;

public interface IClientService {

	public ResultModel updateStatus(String clientID, String status);

	public ResultModel deleteClient(String[] ids);
	
	public ResultModel addClient(Client client);

	public Client getClientById(String id);

	public Client getClientByName(String clientName);


}
