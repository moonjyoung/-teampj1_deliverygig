package com.deliverygig.moonjyoung.vo.mycart;

import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;

import lombok.Data;

@Data
public class BasketFoodVO {
    private Long seq;
    private String menu;
    private Integer price;

    public BasketFoodVO(FoodMenuInfoEntity entity) {
        this.seq = entity.getFmiSeq();
        this.menu = entity.getFmiName();
        this.price = (int)(entity.getFmiPrice()*(1-entity.getFoodCategoryEntity().getStoreInfoEntity().getSiDiscount()));
    }
}
