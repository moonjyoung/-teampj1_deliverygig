package com.deliverygig.moonjyoung.repository.mycart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.food.FoodDetailOptionEntity;
import com.deliverygig.moonjyoung.entity.mycart.PickFoodDetailOptionEntity;

@Repository
public interface PickFoodDetailOptionRepository extends JpaRepository<PickFoodDetailOptionEntity, Long> {
    @Query(value = "select * from pick_food_detail_option a left outer join food_detail_option b on a.pfdo_fdo_seq=b.fdo_seq where a.pfdo_fdo_seq = :fdoSeq", nativeQuery = true)
    public List<FoodDetailOptionEntity> findByPfdoFdoSeq(@Param("fdoSeq") Long pfdoFdoSeq);
}
