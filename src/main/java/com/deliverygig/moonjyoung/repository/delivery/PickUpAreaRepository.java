package com.deliverygig.moonjyoung.repository.delivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;

@Repository
public interface PickUpAreaRepository extends JpaRepository<PickUpAreaEntity, Long> {
    public PickUpAreaEntity findByPuaSeq(Long puaSeq);
    @Query(value = "select * from pick_up_area where pua_ui_seq = :puaUiSeq", nativeQuery = true)
    public List<PickUpAreaEntity> findAllByPuaUiSeq(@Param("puaUiSeq") Long puaUiSeq);
}
