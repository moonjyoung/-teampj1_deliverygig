package com.deliverygig.moonjyoung.repository.mycart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.mycart.PickFoodMenuEntity;

@Repository
public interface PickFoodMenuRepository extends JpaRepository<PickFoodMenuEntity, Long> {
    
}
