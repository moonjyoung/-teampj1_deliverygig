package com.deliverygig.moonjyoung.repository.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;
import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;

@Repository
public interface StoreTimeDetailRepository extends JpaRepository<StoreTimeDetailEntity, Long> {
    public StoreTimeDetailEntity findByStoreInfoEntity(StoreInfoEntity storeInfoEntity);
}
