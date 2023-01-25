package com.deliverygig.moonjyoung.repository.delivery;

import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;

@Repository
public interface UnivTimeInfoRepository extends JpaRepository<UnivTimeInfoEntity, Long>{
    public Integer countByUtiSeq(Long utiSeq);
    public UnivTimeInfoEntity findByUtiSeq(Long utiSeq);
    public Integer countByUtiCloseTime(LocalTime utiCloseTime);
}
