package com.tempest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tempest.entity.BaseEntity;

@Service
public interface BaseService<T> {
	List<T> findAll();

	List<T> findAllById(Iterable<String> ids);

	<s extends T> List<s> saveAll(Iterable<s> entities);

	<s extends T> s save(s entity);

	<s extends T> s saveAndFlush(s entity);

	T get(String id);

	long count();

	void deleteById(String id);

	void delete(T entity);

	void deleteAll(Iterable<? extends T> entities);

	void deleteInBatch(Iterable<T> entities);

	T create(T entity);

	/**
	 * 只保存非空字段
	 * 
	 * @param t
	 * @return
	 */
	T saveNotNull(T t);
}