package com.deliverygig.moonjyoung.repository.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;

@Repository
public interface UnivTimeInfoRepository extends JpaRepository<UnivTimeInfoEntity, Long>{
    
}
