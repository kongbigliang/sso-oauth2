package com.kongbig.model;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ResultModel {

	private String code;// 操作成功为001，失败为000
	private String message;
	private Object data;
	private boolean isNull;

	public ResultModel(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public ResultModel(String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
		if (data == null) {
			this.isNull = true;
		}
	}

	public ResultModel(String code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}

	public ResultModel(String code, String message, Object data, boolean isNull) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
		this.isNull = isNull;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public void sendData(HttpServletResponse response) {
		Gson g = new Gson();
		try {
			response.getWriter().append(g.toJson(this)).flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
