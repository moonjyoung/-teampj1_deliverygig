package com.deliverygig.moonjyoung.repository.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;

@Repository
public interface UnivInfoRepository extends JpaRepository<UnivInfoEntity, Long> {
    public Integer countByUiSeq(Long uiSeq);
    public Integer countByUiName(String uiName);
    public UnivInfoEntity findByUiSeq(Long uiSeq);
    public UnivInfoEntity findByUiName(String uiName);
}
