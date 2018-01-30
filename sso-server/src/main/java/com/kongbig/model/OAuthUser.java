package com.kongbig.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class OAuthUser implements Serializable {

	private String id;
	private String username;
	private String password;
	private String sex;
	private String phone;
	private String status;
	private String remark;
	private String creator;
	private Date createdTime;
	private String lastUpdate;
	private Date lastUpdateTime;

	public OAuthUser() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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
		return "OAuthUser [id=" + id + ", username=" + username + ", password=" + password + ", sex=" + sex + ", phone="
				+ phone + ", status=" + status + ", remark=" + remark + ", creator=" + creator + ", createdTime="
				+ createdTime + ", lastUpdate=" + lastUpdate + ", lastUpdateTime=" + lastUpdateTime + "]";
	}

}
