package com.tempest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.tempest.entity.PkSubject;
import com.tempest.entity.PkUser;
import com.tempest.repository.CoreRepository;
import com.tempest.repository.PkUserRepository;
import com.tempest.shiro.User;
import com.tempest.util.RSAUtil;

@Service
public class UserService extends BaseServiceImpl<PkUser> {
	public User getUserByUsername(String name) {
		return null;
	}
	@Autowired
    private PkUserRepository repo;
	
	protected CoreRepository getRepository() {
        return repo;
    }

	@Override
	public List<PkUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PkUser login(String username,String password) {
		PkUser user = new PkUser();
		user.setUsername(username);
		ExampleMatcher matcher = ExampleMatcher.matching()
		        .withMatcher("username" ,ExampleMatcher.GenericPropertyMatchers.exact());
		Example<PkUser> exam = Example.of(user,matcher);
		password=RSAUtil.decryptByPrivateKey(password);
		List<PkUser> userList = repo.findAll(exam);
		if(userList!=null && userList.size()>0) {
			user = userList.get(0);
			if(password == null || password.equals("")|| !password.equals(user.getPassword()))
				return null;
		}else {
			return null;
		}
		return user;
		
	}
}
