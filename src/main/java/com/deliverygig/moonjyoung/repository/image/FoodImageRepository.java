package com.deliverygig.moonjyoung.repository.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.image.FoodImageEntity;

@Repository
public interface FoodImageRepository extends JpaRepository<FoodImageEntity, Long> {
  // public List<FoodImageEntity> findByFiUri(String fiUri);
  public void findByFiSeq(Long fiSeq);
  public Integer  countByFiSeq(Long fiSeq);
  public Long deleteByFiSeq(Long fiSeq);
   
  public FoodImageEntity findTopByFiUri (String FiUri);
  
   
  // select * from todo_images_info where tii_uri = [uri] order by tii_seq desc limit 1;
  
  @Query(value = "select * from food_info_image where fi_fmi_seq = :fi_fmi_seq", nativeQuery = true)
  public FoodImageEntity findByFiFmiSeq(@Param("fi_fmi_seq") Long fiFmiSeq);


}