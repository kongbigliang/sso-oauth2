package com.kongbig.dao;

import org.springframework.stereotype.Repository;

import com.kongbig.model.Client;

@Repository
public interface ClientDao {
	Client getClientById(String id);

	boolean addClient(Client client);

	boolean updateStatus(String clientID, String status);

	boolean deleteClient(String[] ids);

	Client getClientByName(String clientName);

	Client getClientModel(String clientID, String clientSecret);
}
