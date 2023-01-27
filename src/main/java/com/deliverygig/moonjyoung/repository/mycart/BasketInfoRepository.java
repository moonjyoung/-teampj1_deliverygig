package com.deliverygig.moonjyoung.repository.mycart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.mycart.BasketInfoEntity;

@Repository
public interface BasketInfoRepository extends JpaRepository<BasketInfoEntity, Long> {
    @Query(value = "select * from basket_info where bi_ci_seq = :biCiSeq and bi_status = :biStatus order by bi_seq desc limit 1", nativeQuery = true)
    public BasketInfoEntity findByBiCiSeqAndBiStatus(@Param("biCiSeq") Long biCiSeq, @Param("biStatus") Integer biStatus);
}
