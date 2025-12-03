package com.tempest.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tempest.entity.PkSubject;

@Repository
@Transactional
public interface PkSubjectRepository  extends CoreRepository<PkSubject> {

}
