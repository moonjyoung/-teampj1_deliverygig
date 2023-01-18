package com.deliverygig.moonjyoung.repository.mycart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.mycart.FoodBasketEntity;

@Repository
public interface FoodBasketRepository extends JpaRepository<FoodBasketEntity, Long> {
    
}
