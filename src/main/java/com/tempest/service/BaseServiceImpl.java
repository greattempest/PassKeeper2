package com.tempest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tempest.entity.BaseEntity;

@Service
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

	public List<T> findAll() {
		return null;
	}

	public List<T> findAllById(Iterable<Long> ids) {
		return null;
	}

	public <s extends T> List<s> saveAll(Iterable<s> entities) {
		return null;
	}

	public <s extends T> s save(s entity) {
		return null;
	}

	public <s extends T> s saveAndFlush(s entity) {
		return null;
	}

	public T get(String id) {
		return null;
	}

	public long count() {
		return 0L;
	}

	public void deleteById(String id) {

	}

	public void delete(T entity) {

	}

	public void deleteAll(Iterable<? extends T> entities) {

	}

	public void deleteInBatch(Iterable<T> entities) {

	}

	public T create(T entity) {
		return null;
	}

	/**
	 * 只保存非空字段
	 * 
	 * @param t
	 * @return
	 */
	public T saveNotNull(T t) {
		return null;
	}
}