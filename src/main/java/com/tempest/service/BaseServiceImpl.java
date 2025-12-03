package com.tempest.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.tempest.entity.BaseEntity;
import com.tempest.repository.CoreRepository;

@Service
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> { 
	
    //@Autowired
    //private JpaRepository<T, Long> baseRepository;
    
    protected abstract CoreRepository<T> getRepository();
    
	public List<T> findAll(Example<T> example) {
		return this.getRepository().findAll(example);
	}

	public List<T> findAllById(Iterable<String> ids) {
		return this.getRepository().findAllById(ids);
	}
	@Override
	public <s extends T> List<s> saveAll(Iterable<s> entities) {
		return this.getRepository().saveAll(entities);
	}
	@Override
	public <s extends T> s save(s entity) {
		return this.getRepository().save(entity);
	}
	@Override
	public <s extends T> s saveAndFlush(s entity) {
		return this.getRepository().saveAndFlush(entity);
	}
	@Override
	public T get(String id) {
        T entity =  this.getRepository().getById(id);
        return entity;

	}

	public long count() {
		return this.getRepository().count();
	}

	public void deleteById(String id) {
		this.getRepository().deleteById(id);
	}

	public void delete(T entity) {
		this.getRepository().delete(entity);
	}

	public void deleteAll(Iterable<? extends T> entities) {
		this.getRepository().deleteAll(entities);
	}

	public void deleteInBatch(Iterable<T> entities) {
		this.getRepository().deleteInBatch(entities);
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