package com.deliverygig.moonjyoung.repository.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.image.FoodImageEntity;

@Repository
public interface FoodImageRepository extends JpaRepository<FoodImageEntity, Long> {
  public List<FoodImageEntity> findByfiSeq(Long fiSeq);
  // select * from todo_images_info where tii_uri = [uri] order by tii_seq desc limit 1;
  public List<FoodImageEntity> findByfiUri(String fiUri);
}