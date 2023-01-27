package com.deliverygig.moonjyoung.vo.mycart;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.deliverygig.moonjyoung.entity.mycart.BasketInfoEntity;

import lombok.Data;

@Data
public class ShowBasketVO {
    private String ciName;
    private String biNumber;
    private LocalDateTime biRegDt;
    private String puaName;
    private LocalTime pickupTime;
    private String menuOptions;
    private Integer price;

    public ShowBasketVO(BasketInfoEntity entity) {
        this.ciName = entity.getCustomerInfoEntity().getCiName();
        this.biNumber = entity.getBiNumber();
        this.biRegDt = entity.getBiRegDt();
        this.puaName = entity.getBiPuaName();
        this.pickupTime = entity.getBiPickupTime();
        this.menuOptions = entity.getBiMenuOption();
        this.price = entity.getBiPrice();
    }
}
