package com.tempest.jdbc.dao;

import com.tempest.jdbc.model.Subject;
import com.tempest.jdbc.model.SubjectExample;
import java.util.List;

public interface SubjectMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pk_subject
	 * @mbg.generated  Mon Jun 22 14:12:10 CST 2020
	 */
	long countByExample(SubjectExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pk_subject
	 * @mbg.generated  Mon Jun 22 14:12:10 CST 2020
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pk_subject
	 * @mbg.generated  Mon Jun 22 14:12:10 CST 2020
	 */
	int insert(Subject record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pk_subject
	 * @mbg.generated  Mon Jun 22 14:12:10 CST 2020
	 */
	int insertSelective(Subject record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pk_subject
	 * @mbg.generated  Mon Jun 22 14:12:10 CST 2020
	 */
	List<Subject> selectByExample(SubjectExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pk_subject
	 * @mbg.generated  Mon Jun 22 14:12:10 CST 2020
	 */
	Subject selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pk_subject
	 * @mbg.generated  Mon Jun 22 14:12:10 CST 2020
	 */
	int updateByPrimaryKeySelective(Subject record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pk_subject
	 * @mbg.generated  Mon Jun 22 14:12:10 CST 2020
	 */
	int updateByPrimaryKey(Subject record);
}