package com.kongbig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongbig.dao.ClientDao;
import com.kongbig.model.Client;
import com.kongbig.model.ResultModel;
import com.kongbig.service.IClientService;
import com.kongbig.util.UUIDUtil;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private ClientDao clientDao;

	@Override
	public ResultModel updateStatus(String clientID, String status) {
		if (clientDao.updateStatus(clientID, status)) {
			return new ResultModel("001", "修改成功！");
		}
		return new ResultModel("000", "修改失败！");
	}

	@Override
	public ResultModel deleteClient(String[] ids) {
		if (clientDao.deleteClient(ids)) {
			return new ResultModel("001", "删除成功！");
		}
		return new ResultModel("000", "删除失败！");
	}

	@Override
	public ResultModel addClient(Client client) {
		// 按应用名查询,有则添加失败
		Client ClientTemp = clientDao.getClientByName(client.getClientName());
		if (ClientTemp != null && ClientTemp.getClientID() != null) {
			return new ResultModel("002", "应用名已存在，请重新输入!");
		}
		client.setClientID("301" + UUIDUtil.getUUID().substring(0, 5));
		client.setStatus("000");
		if (!clientDao.addClient(client)) {
			return new ResultModel("000", "新增应用失败！");
		}
		return new ResultModel("001", "新增应用成功！", client);
	}

	@Override
	public Client getClientById(String id) {
		return clientDao.getClientById(id);
	}

	@Override
	public Client getClientByName(String clientName) {
		return clientDao.getClientByName(clientName);
	}

}
