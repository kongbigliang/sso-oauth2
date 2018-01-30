package com.kongbig.model;

import java.util.Date;

/**
 * accessToken,refreshToken,创建时间,accessToken生存时间,用户账号;
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

	public AccessTokenModel(String accessToken, Date createTime, String expiresIn, String refreshToken,
			String account) {
		this.accessToken = accessToken;
		this.createTime = createTime;
		this.expiresIn = expiresIn;
		this.refreshToken = refreshToken;
		this.account = account;
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
