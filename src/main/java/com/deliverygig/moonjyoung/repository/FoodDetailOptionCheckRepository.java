package com.deliverygig.moonjyoung.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.FoodDetailOptionCheckEntity;

@Repository
public interface FoodDetailOptionCheckRepository extends JpaRepository<FoodDetailOptionCheckEntity, Long> {
    public List<FoodDetailOptionCheckEntity> findByFdocCiSeq(Long fdocCiSeq);
    @Query(value = "select * from food_detail_option_check where fdoc_fdo_seq = :fdoSeq", nativeQuery = true)
    public List<FoodDetailOptionCheckEntity> findByFdocFdoSeq(@Param("fdoSeq") Long fdocFdoSeq);
    @Query(value = "select * from food_detail_option_check where fdoc_ci_seq = :ciSeq and fdoc_fdo_seq = :fdoSeq", nativeQuery = true)
    public List<FoodDetailOptionCheckEntity> findByFdocCiSeqAndFdocFdoSeq(@Param("ciSeq") Long fdocCiSeq, @Param("fdoSeq") Long fdocFdoSeq);
}
