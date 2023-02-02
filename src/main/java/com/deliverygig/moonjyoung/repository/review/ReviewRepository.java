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
    @Query(value = "select count(*) from review_info a left join basket_menu_options_combine b on a.ri_bmoc_seq=b.bmoc_seq left join store_time_detail c on b.bmoc_std_seq=c.std_seq left join store_info d on c.std_si_seq=d.si_seq where d.si_seq = :siSeq", nativeQuery = true)
    public Integer countBySiSeq(@Param("siSeq") Long siSeq);
    @Query(value = "select avg(ri_score) from review_info a left join basket_menu_options_combine b on a.ri_bmoc_seq=b.bmoc_seq left join store_time_detail c on b.bmoc_std_seq=c.std_seq left join store_info d on c.std_si_seq=d.si_seq where d.si_seq = :siSeq", nativeQuery = true)
    public Double findAvgRiScoreBySiSeq(@Param("siSeq") Long siSeq);
    public Page<ReviewEntity> findByriContentsContains(String riContents, Pageable pageable);
    public ReviewEntity findByRiSeq(Long riSeq);
}
