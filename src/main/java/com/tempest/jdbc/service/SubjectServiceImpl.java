package com.tempest.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tempest.jdbc.dao.SubjectMapper;
import com.tempest.jdbc.model.Subject;
import com.tempest.jdbc.model.SubjectExample;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private SubjectMapper mapper;

	@Override
	public Subject selectById(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Subject> selectByUserid(String userid) {
		// TODO Auto-generated method stub
		SubjectExample example = new SubjectExample();
		SubjectExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		List<Subject> subjectList = mapper.selectByExample(example);
		
		return subjectList;
	}

	@Override
	public long countByUserid(String userid) {
		// TODO Auto-generated method stub
		SubjectExample example = new SubjectExample();
		SubjectExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		return mapper.countByExample(example);
		
	}
	
	@Override
	public int insert(Subject subject) {
		if(subject==null)
			subject = new Subject();
		return mapper.insert(subject);
	}
	
	@Override
	public int deleteById(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

}
