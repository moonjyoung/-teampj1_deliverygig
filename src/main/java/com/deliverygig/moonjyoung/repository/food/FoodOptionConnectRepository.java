package com.deliverygig.moonjyoung.repository.food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.food.FoodOptionConnectEntity;

@Repository
public interface FoodOptionConnectRepository extends JpaRepository<FoodOptionConnectEntity, Long> {
    @Query(value = "select * from food_option_connect where foc_fmi_seq = :focFmiSeq order by foc_fmo_order", nativeQuery = true)
    public List<FoodOptionConnectEntity> findAllByFocFmiSeq(@Param("focFmiSeq") Long focFmiSeq);
    // public FoodMenuOptionEntity findByFocFmoSeq();
}
