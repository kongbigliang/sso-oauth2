package com.kongbig.model;

import java.util.Date;

public class Client {

	private String clientID;
	private String clientName;
	private String clientSecret;
	private String ip;
	private String domainName;
	private String status;
	private Date createdTime;
	private String lastUpdate;
	private Date lastUpdateTime;

	public Client() {

	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String state) {
		this.status = state;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public String toString() {
		return "OAuthUser [clientID=" + clientID + ", clientSecret=" + clientSecret + ", ip=" + ip + ", domainName="
				+ domainName + ", status=" + status + ", createdTime=" + createdTime + ", lastUpdate=" + lastUpdate
				+ ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}
