package com.tempest.jdbc.service;

import com.tempest.jdbc.model.User;

public interface UserService {
	public User selectById(String id);

	public User selectByUsername(String username);

	public int insert(User user);
	
	public com.tempest.shiro.User getUserByUsername(String username);
}
