package com.tempest.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tempest.jdbc.dao.AccountMapper;
import com.tempest.jdbc.model.Account;
import com.tempest.jdbc.model.AccountExample;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper mapper;

	@Override
	public Account selectById(int id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Account selectByAccount(String account) {
		// TODO Auto-generated method stub
		
		AccountExample example = new AccountExample();
		AccountExample.Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account);
		List<Account>list = mapper.selectByExample(example);
		if(list.size()>0)
			return list.get(0);
		
		return null;
	}
	
	@Override
	public int insert(Account account) {
		if(account==null)
			account = new Account();
		return mapper.insert(account);
	}


}
