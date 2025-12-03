package com.tempest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tempest.entity.PkSubject;
import com.tempest.repository.CoreRepository;
import com.tempest.repository.PkSubjectRepository;

@Service
public class SubjectService extends BaseServiceImpl<PkSubject> {
	@Autowired
    private PkSubjectRepository repo;
	
	protected CoreRepository getRepository() {
        return repo;
    }

	@Override
	public List<PkSubject> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
