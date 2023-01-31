package com.deliverygig.moonjyoung.repository.image;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.image.FoodImageEntity;

@Repository
public interface FoodImageRepository extends JpaRepository<FoodImageEntity, Long> {
  // public List<FoodImageEntity> findByFiUri(String fiUri);
  public void findByFiSeq(Long fiSeq);
  public Integer  countByFiSeq(Long fiSeq);
  public Long deleteByFiSeq(Long fiSeq);
   
  public FoodImageEntity findTopByFiUri (String FiUri);
  
  public FoodImageEntity findByFiFmiSeq(Long fiFmiSeq);
  // select * from todo_images_info where tii_uri = [uri] order by tii_seq desc limit 1;
  



}