package com.tempest.jdbc.service;

import java.security.Permissions;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tempest.jdbc.dao.UserMapper;
import com.tempest.jdbc.model.User;
import com.tempest.jdbc.model.UserExample;
import com.tempest.shiro.Permission;
import com.tempest.shiro.Role;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper mapper;
	
	public User selectById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	public User selectByUsername(String username) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> userList = mapper.selectByExample(example);
		if(userList.size()>0)
			return userList.get(0);
		else 
			return new User();
	}

	public int insert(User user) {
		return mapper.insert(user);
	}
	
	public com.tempest.shiro.User getUserByUsername(String username){
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> userList = mapper.selectByExample(example);
		User u = userList.size()>0?userList.get(0):null;
		com.tempest.shiro.User shiroUser = new com.tempest.shiro.User();
		if(u!=null) {
			shiroUser.setId(u.getId());
			shiroUser.setUserName(u.getUsername());
			shiroUser.setPassword(u.getPassword());
			
	        Permission permissions1 = new Permission("1","query");
	        Permission permissions2 = new Permission("2","add");
	        Set<Permission> permissionsSet = new HashSet<>();
	        permissionsSet.add(permissions1);
	        permissionsSet.add(permissions2);
	        Role role = new Role("1","admin",permissionsSet);
	        Set<Role> roleSet = new HashSet<>();
	        roleSet.add(role);

			shiroUser.setRoles(roleSet);
		}
		return shiroUser;
		
	}

}
