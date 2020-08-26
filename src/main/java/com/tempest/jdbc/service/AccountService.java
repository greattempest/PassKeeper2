package com.tempest.jdbc.service;


import com.tempest.jdbc.model.Account;

public interface AccountService {
	
	Account selectById(int id);
	
	Account selectByAccount(String account);
	
	int insert(Account account);
}
