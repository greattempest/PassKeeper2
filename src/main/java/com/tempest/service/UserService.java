package com.tempest.service;

import org.springframework.stereotype.Service;

import com.tempest.entity.PkUser;
import com.tempest.shiro.User;

@Service
public class UserService extends BaseServiceImpl<PkUser> {
	public User getUserByUsername(String name) {
		return null;
	}
}
