package com.kongbig.model;

import java.util.Date;

/**
 * 
 * @author kongbig
 *
 */
public class AccessTokenModel {

	private String accessToken;
	private Date createTime;
	private String expiresIn;
	private String refreshToken;
	private String account;

	public AccessTokenModel() {
		
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "AccessTokenModel [accessToken=" + accessToken + ", createTime=" + createTime + ", expiresIn="
				+ expiresIn + ", refreshToken=" + refreshToken + ", account=" + account + "]";
	}

}
