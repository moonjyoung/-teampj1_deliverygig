package com.deliverygig.moonjyoung.entity.mycart;

import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.entity.food.FoodDetailOptionEntity;

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
@Table(name = "food_detail_option_check")
public class FoodDetailOptionCheckEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fdoc_seq") private Long fdocSeq;
    @Column(name = "fdoc_ci_seq") private Long fdocCiSeq;
    // @Column(name = "fdoc_fdo_seq") private Long fdocFdoSeq;

    @ManyToOne @JoinColumn(name = "fdoc_fdo_seq") private FoodDetailOptionEntity foodDetailOptionEntity;
}
