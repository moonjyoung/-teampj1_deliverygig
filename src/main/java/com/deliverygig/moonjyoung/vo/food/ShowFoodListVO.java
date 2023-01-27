package com.deliverygig.moonjyoung.vo.food;

import java.util.ArrayList;
import java.util.List;

import com.deliverygig.moonjyoung.entity.food.FoodDetailOptionEntity;
import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;
import com.deliverygig.moonjyoung.entity.food.FoodMenuOptionEntity;

import lombok.Data;

@Data
public class ShowFoodListVO {
    private String cateName;
    private List<ShowMenuInfoVO> menuList;
    private Long fiFmiSeq;
    // 이미지정보 나중에 추가
}