package com.deliverygig.moonjyoung.entity.mycart;

import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;

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
@Table(name = "pick_food_menu")
public class PickFoodMenuEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pfm_seq") private Long pfmSeq;
    // @Column(name = "pfm_fb_seq") private Long pfmFbSeq;
    // @Column(name = "pfm_fmi_seq") private Long pfmFmiSeq;
    @Column(name = "pfm_count") private Integer pfmCount;
    @Column(name = "pfm_price") private Integer pfmPrice;

    @ManyToOne @JoinColumn(name = "pfm_fb_seq") FoodBasketEntity foodBasketEntity;
    @ManyToOne @JoinColumn(name = "pfm_fmi_seq") FoodMenuInfoEntity foodMenuInfoEntity;

    public void setPrice(Integer price) {
        this.pfmPrice = price;
    }
}
