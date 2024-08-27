package com.tempest.util;

public class Response {
	private Integer code;
	private String msg;
	private Object content;

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getContent() {
		return this.content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}