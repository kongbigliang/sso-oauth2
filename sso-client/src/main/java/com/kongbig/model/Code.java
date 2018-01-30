package com.kongbig.model;

import java.util.Date;

public class Code {

	private String code;
	private Date createTime;
	private String expiresIn;
	private String accountFk;

	public Code() {
		super();
	}

	public Code(String accountFk) {
		super();
		this.accountFk = accountFk;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getAccountFk() {
		return accountFk;
	}

	public void setAccountFk(String accountFk) {
		this.accountFk = accountFk;
	}

	@Override
	public String toString() {
		return "Code [code=" + code + ", createTime=" + createTime + ", expiresIn=" + expiresIn + ", accountFk="
				+ accountFk + "]";
	}

}
