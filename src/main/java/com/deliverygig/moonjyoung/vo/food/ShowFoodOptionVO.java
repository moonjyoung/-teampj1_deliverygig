package com.deliverygig.moonjyoung.vo.food;

import com.deliverygig.moonjyoung.entity.food.FoodDetailOptionEntity;

import lombok.Data;

@Data
public class ShowFoodOptionVO {
    private String optionName;
    private Integer optionPrice;

    public ShowFoodOptionVO(FoodDetailOptionEntity fdoEntity) {
        this.optionName = fdoEntity.getFdoName();
        this.optionPrice = fdoEntity.getFdoPrice();
    }
}
