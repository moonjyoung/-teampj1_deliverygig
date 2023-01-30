package com.deliverygig.moonjyoung.vo.mycart;

import java.time.LocalTime;

import com.deliverygig.moonjyoung.entity.mycart.BasketMenuOptionsCombineEntity;

import lombok.Data;

@Data
public class ShowBasketMenuVO {
    private String siName;
    private LocalTime closeTime;
    private LocalTime deliveryTime;
    private String menuName;
    private String optionAll;
    private Integer price;
    private Integer count;

    public ShowBasketMenuVO(BasketMenuOptionsCombineEntity entity) {
        this.siName = entity.getFoodMenuInfoEntity().getFoodCategoryEntity().getStoreInfoEntity().getSiName();
        this.closeTime = entity.getStoreTimeDetailEntity().getStdCloseTime();
        this.deliveryTime = entity.getStoreTimeDetailEntity().getUnivTimeInfoEntity().getUtiPickupTime1();
        this.menuName = entity.getFoodMenuInfoEntity().getFmiName();
        this.optionAll = entity.getBmocOptionAll();
        this.price = entity.getBmocPrice();
        this.count = entity.getBmocCount();
    }
}
