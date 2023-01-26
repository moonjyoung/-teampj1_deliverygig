package com.deliverygig.moonjyoung.vo.food;

import com.deliverygig.moonjyoung.entity.food.FoodDetailOptionEntity;

import lombok.Data;

@Data
public class ShowFoodDetailOptionVO {
    private String optionName;
    private Integer optionPrice;

    public ShowFoodDetailOptionVO(FoodDetailOptionEntity fdoEntity) {
        this.optionName = fdoEntity.getFdoName();
        this.optionPrice = fdoEntity.getFdoPrice();
    }

    public ShowFoodDetailOptionVO() {}
}
