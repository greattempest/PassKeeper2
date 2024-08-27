package com.tempest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tempest.entity.BaseEntity;

@Service
public interface BaseService<T> {
	List<T> findAll();

	List<T> findAllById(Iterable<Long> ids);

	<s extends T> List<s> saveAll(Iterable<s> entities);

	<s extends T> s save(s entity);

	<s extends T> s saveAndFlush(s entity);

	T get(Long id);

	long count();

	void deleteById(Long id);

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