package com.deliverygig.moonjyoung.vo.food;

import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;

import lombok.Data;

@Data
public class ShowMenuInfoVO {
    private String name;
    private Integer price;
    private Integer discountPrice;

    public ShowMenuInfoVO(FoodMenuInfoEntity entity) {
        this.name = entity.getFmiName();
        this.price = entity.getFmiPrice();
        this.discountPrice = (int)(entity.getFmiPrice()*(1-entity.getFoodCategoryEntity().getStoreInfoEntity().getSiDiscount()));
    }
}
