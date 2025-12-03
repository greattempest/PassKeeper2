package com.tempest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.tempest.entity.PkAccount;
import com.tempest.repository.CoreRepository;
import com.tempest.repository.PkUserRepository;

@Service
public class AccountService extends BaseServiceImpl<PkAccount> {
	
	@Autowired
    private PkUserRepository repo;
	
	protected CoreRepository getRepository() {
        return repo;
    }
	
	@Override
	public List<PkAccount> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}