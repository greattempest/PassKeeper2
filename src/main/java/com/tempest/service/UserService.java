package com.tempest.service;

import java.util.Date;
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
			String userPassword=user.getPassword();
			userPassword=RSAUtil.decryptByPrivateKey(userPassword);
			if(user.getLockdate()!=null && new Date().getTime()<(user.getLockdate().getTime()+900*1000))
				return user;
			if(password == null || password.equals("")|| !password.equals(userPassword)) {
				if(user.getErrorcount()==null)
					user.setErrorcount(1);
				else
					user.setErrorcount(user.getErrorcount()+1);
				if(user.getErrorcount()>3) {
					user.setLockdate(new Date());
				}
				repo.save(user);
				return null;
			}
		}else {
			return null;
		}
		user.setErrorcount(0);
		user.setLogindate(new Date());
		repo.save(user);
		return user;
		
	}
}
