package com.deliverygig.moonjyoung.entity.mycart;

import java.time.LocalDateTime;

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
@Table(name = "pick_food_detail_option")
public class PickFoodDetailOptionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pfdo_seq") private Long pfdoSeq;
    // @Column(name = "fdop_fp_seq") private Long fdopFpSeq;
    // @Column(name = "fdop_fmi_seq") private Long fdopFmiSeq;
    // @Column(name = "fdop_fdo_seq") private Long fdopFdoSeq;
    @Column(name = "pfdo_reg_dt") private LocalDateTime pfdoRegDt;

    @ManyToOne @JoinColumn(name = "pfdo_pfm_seq") PickFoodMenuEntity pickFoodMenuEntity;
    // @ManyToOne @JoinColumn(name = "pfdo_fmi_seq") FoodMenuInfoEntity foodMenuInfoEntity;
    @ManyToOne @JoinColumn(name = "pfdo_fdo_seq") FoodDetailOptionEntity foodDetailOptionEntity;
}
