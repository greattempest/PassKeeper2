package com.tempest.jdbc.service;

import java.util.List;

import com.tempest.jdbc.model.Subject;

public interface SubjectService {
	public Subject selectById(String id);
	
	public List<Subject> selectByUserid(String userid);
	
	public long countByUserid(String userid);
	
	public int insert(Subject subject);
	
	public int deleteById(String id);
}
