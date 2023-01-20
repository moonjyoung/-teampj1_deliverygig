package com.deliverygig.moonjyoung.repository.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.food.FoodCategoryEntity;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategoryEntity, Long> {
    public FoodCategoryEntity findByFcSeq(Long fcSeq);
    public Integer countByFcName(String fcName);
    public void deleteByFcSeq(Long fcSeq);
    
}
