package com.deliverygig.moonjyoung.repository.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.deliverygig.moonjyoung.entity.review.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query(value = "select count(*) from review_info where ri_bmoc_seq = :riBmocSeq", nativeQuery = true)
    public Integer countByRiBmocSeq(@Param("riBmocSeq") Long riBmocSeq);
    public Page<ReviewEntity> findByriContentsContains(String riContents, Pageable pageable);
    public ReviewEntity findByRiSeq(Long riSeq);
}
