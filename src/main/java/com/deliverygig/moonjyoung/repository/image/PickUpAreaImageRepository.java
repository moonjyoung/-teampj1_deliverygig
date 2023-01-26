package com.deliverygig.moonjyoung.repository.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.image.PickUpAreaImageEntity;

@Repository
public interface PickUpAreaImageRepository extends JpaRepository <PickUpAreaImageEntity , Long>{
    public List<PickUpAreaEntity> findByPuaiUri (String puaiUri);
    public void deleteByPuaiSeq (Long puaiSeq);
    public Integer countByPuaiSeq(Long puaiSeq );
    
}


