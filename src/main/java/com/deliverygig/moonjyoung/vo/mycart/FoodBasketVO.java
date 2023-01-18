package com.deliverygig.moonjyoung.vo.mycart;

import java.util.List;

import lombok.Data;

@Data
public class FoodBasketVO {
    private Long ciSeq;
    private String menuName;
    private List<String> menuOptionList;
    private Integer menuPrice;
    private Integer optionPrice;
    private Integer totalPrice;
    private Integer count;
}
