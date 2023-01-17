package com.deliverygig.moonjyoung.repository.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.account.MasterEntity;

@Repository
public interface MasterRepository extends JpaRepository<MasterEntity, Long> {
    Page<MasterEntity> findAll(Pageable page);
    Integer countByMiId(String miId);
    MasterEntity findByMiIdAndMiPwd(String miId, String miPwd);
    MasterEntity findByMiSeq(Long miSeq);
}
