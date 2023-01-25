package com.deliverygig.moonjyoung.repository.food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.food.FoodCategoryEntity;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategoryEntity, Long> {
    @Query(value = "select * from food_category where fc_si_seq = :siSeq order by fc_order", nativeQuery = true)
    public List<FoodCategoryEntity> findAllBySiSeq(@Param("siSeq") Long siSeq);
}
