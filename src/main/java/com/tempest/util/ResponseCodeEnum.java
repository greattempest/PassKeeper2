package com.tempest.util;

public enum ResponseCodeEnum {
	SUCCESS(Integer.valueOf(0), "请求成功！"), SYSTEM_ERROR(Integer.valueOf(-1), "系统出错！");

	private Integer code;
	private String msg;

	private ResponseCodeEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public Integer getCode() {
		return this.code;
	}
}