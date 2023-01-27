package com.deliverygig.moonjyoung.vo.mycart;

import java.time.LocalTime;

import lombok.Data;

@Data
public class GetBasketMenuOptionVO {
    private Long biSeq;
    private LocalTime deliveryTime;
    private String menuName;
    private String optionAll;
    private Integer price;

    // public BasketMenuOptionVO(StoreTimeDetailEntity stdEntity, FoodMenuInfoEntity fmiEntity) {
    //     this.deliveryTime = stdEntity.getUnivTimeInfoEntity().getUtiPickupTime1();
    //     this.menuName = fmiEntity.getFmiName();
    //     this.price = fmiEntity.getFmiPrice();
    // }
}
