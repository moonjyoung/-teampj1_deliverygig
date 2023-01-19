package com.deliverygig.moonjyoung.repository.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;

@Repository
public interface PickUpAreaRepository extends JpaRepository<PickUpAreaEntity, Long> {
    public PickUpAreaEntity findByPuaSeq(Long puaSeq);
}
