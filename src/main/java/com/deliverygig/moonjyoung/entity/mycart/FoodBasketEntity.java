package com.deliverygig.moonjyoung.entity.mycart;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "food_basket")
public class FoodBasketEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fb_seq") private Long fbSeq;
    @Column(name = "fb_number") private String fbNumber;
    @Column(name = "fb_reg_dt") private LocalDateTime fbRegDt;
    @Column(name = "fb_ci_seq") private Long fbCiSeq;
    // @Column(name = "fb_uti_seq") private Long fbUtiSeq;
    @Column(name = "fb_pua_seq") private Long fbPuaSeq;
    @Column(name = "fb_status") private Integer fbStatus;

    // 고객회원정보 안 넣어서 임시 주석처리. 나중엔 이걸로 가야함
    // @ManyToOne @JoinColumn(name = "fb_ci_seq") private CustomerInfoEntity customerInfoEntity;
    @ManyToOne @JoinColumn(name = "fb_uti_seq") private UnivTimeInfoEntity univTimeInfoEntity;
    // @ManyToOne @JoinColumn(name = "fb_pua_seq") private PickUpAreaEntity pickUpAreaEntity;
}
