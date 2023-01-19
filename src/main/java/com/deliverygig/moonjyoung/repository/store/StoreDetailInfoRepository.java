package com.deliverygig.moonjyoung.repository.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.store.StoreDetailInfoEntity;

@Repository
public interface StoreDetailInfoRepository extends JpaRepository<StoreDetailInfoEntity, Long> {
    StoreDetailInfoEntity findBySdiSeq(Long SdiSeq);
    
}
