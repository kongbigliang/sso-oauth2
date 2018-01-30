package com.kongbig.model;

public class ShopUser {

	private String id;
	private String account;
	private String password;
	private String encodeString;

	public ShopUser() {
		
	}

	public ShopUser(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncodeString() {
		return encodeString;
	}

	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}

	@Override
	public String toString() {
		return "ShopUser [id=" + id + ", account=" + account + ", password=" + password + ", encodeString="
				+ encodeString + "]";
	}

}
