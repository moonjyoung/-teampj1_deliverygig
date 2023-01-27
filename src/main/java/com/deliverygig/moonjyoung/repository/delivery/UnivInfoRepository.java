package com.deliverygig.moonjyoung.repository.delivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;

@Repository
public interface UnivInfoRepository extends JpaRepository<UnivInfoEntity, Long> {
    public Integer countByUiSeq(Long uiSeq);
    public Integer countByUiName(String uiName);
    public UnivInfoEntity findByUiSeq(Long uiSeq);
    //public UnivInfoEntity findByUiName(String uiName);
    public UnivInfoEntity findByUiName(String keyword);


    public List<UnivInfoEntity> findAllByUiNameContaining (String uiName);
}
    // List<BookEntity> searchBookTitle(@Param("keyword") String keyword, @Param("offset") Integer offset);