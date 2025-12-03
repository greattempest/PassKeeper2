package com.tempest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.tempest.entity.BaseEntity;



@NoRepositoryBean
public interface CoreRepository<T extends BaseEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {

}
