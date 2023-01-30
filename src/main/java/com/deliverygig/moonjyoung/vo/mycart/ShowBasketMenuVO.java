package com.deliverygig.moonjyoung.vo.mycart;

import java.time.LocalTime;

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
}
