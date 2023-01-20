package com.deliverygig.moonjyoung.repository.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity, Long> {
<<<<<<< HEAD
    public Integer countBySiName(String siName);
    public StoreInfoEntity findBySiOiSeq(Long siOiSeq);
    public StoreInfoEntity  findBySiSeq(Long siSeq);
=======
    public StoreInfoEntity findBySiSeq(Long siSeq);
>>>>>>> master
}
