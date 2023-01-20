package com.deliverygig.moonjyoung.repository.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;

@Repository
public interface FoodMenuInfoRepository extends JpaRepository<FoodMenuInfoEntity, Long> {
    public Integer countByFmiSeq (Long fmiSeq);
    public Integer countByFmiName(String fmiName);
    public void deleteByFmiSeq(Long fmiSeq);
}
