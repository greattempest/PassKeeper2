package com.tempest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tempest.entity.PkUser;

@Repository
public interface PkUserRepository extends JpaRepository<PkUser, Long>, JpaSpecificationExecutor <PkUser> {

}
