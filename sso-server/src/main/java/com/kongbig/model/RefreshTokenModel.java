package com.kongbig.model;

import java.util.Date;

public class RefreshTokenModel {

	private String refreshToken;
	private Date createTime;
	private String expiresIn;

	public RefreshTokenModel() {
		
	}

	public RefreshTokenModel(String refreshToken, Date createTime, String expiresIn) {
		this.refreshToken = refreshToken;
		this.createTime = createTime;
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
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

	@Override
	public String toString() {
		return "RefreshTokenModel [refreshToken=" + refreshToken + ", createTime=" + createTime + ", expiresIn="
				+ expiresIn + "]";
	}

}
