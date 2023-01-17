package com.deliverygig.moonjyoung.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

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
    @Column(name = "fb_ci_seq") private Long fbCiSeq; // 필요 없는거 아닌가?
    @Column(name = "fb_count") private Integer fbCount;
    @Column(name = "fb_regTime") private LocalDateTime fbRegTime;
}
