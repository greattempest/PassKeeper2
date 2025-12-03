package com.tempest.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query {
	public List<?> list;
	public Map<String, Object> param;
	public List<?> getList() {
		return list;
	}
	public Query() {
		list = new ArrayList<Object>();
		param = new HashMap<String, Object>();
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public Map<String, Object> getParam() {
		return param;
	}
	public void setParam(Map<String, Object> param) {
		this.param = param;
	}
	
	public void addParam(String key, Object value) {
		param.put(key, value);
	}
	public void removeParam(String key) {
		param.remove(key);
	}
	
}
