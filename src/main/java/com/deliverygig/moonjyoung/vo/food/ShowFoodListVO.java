package com.deliverygig.moonjyoung.vo.food;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ShowFoodListVO {
    private String siName;
    private String cateName;
    private String menuName;
    private String optionCateName;
    // private String optionName;
    // private Integer optionPrice;
    private List<Map<Long, String>> optionList;
}