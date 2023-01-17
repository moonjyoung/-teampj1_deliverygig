package com.deliverygig.moonjyoung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.FoodBasketEntity;

@Repository
public interface FoodBasketRepository extends JpaRepository<FoodBasketEntity, Long> {
    
}
