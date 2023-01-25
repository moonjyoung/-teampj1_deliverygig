package com.deliverygig.moonjyoung.repository.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.image.StoreImageEntity;

    
@Repository
public interface StoreImageRepository extends JpaRepository<StoreImageEntity, Long> {
  public List<StoreImageEntity> findBysimgSeq(Long simgSeq);
  // select * from todo_images_info where tii_uri = [uri] order by tii_seq desc limit 1;
  public List<StoreImageEntity> findBysimgUri(String simgUri);
}
