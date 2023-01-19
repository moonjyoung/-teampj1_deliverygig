package com.deliverygig.moonjyoung.vo.food;

import java.util.ArrayList;
import java.util.List;

import com.deliverygig.moonjyoung.entity.food.FoodDetailOptionEntity;
import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;
import com.deliverygig.moonjyoung.entity.food.FoodMenuOptionEntity;

import lombok.Data;

@Data
public class ShowFoodListVO {
    private String siName;
    private String cateName;
    private String menuName;
    private String optionCateName;
    // private String optionName;
    // private Integer optionPrice;
    // private List<Map<Long, String>> optionList;
    private List<ShowFoodOptionVO> optionList;

    public ShowFoodListVO(FoodMenuInfoEntity fmiEntity, FoodMenuOptionEntity fmoEntity) {
        this.siName = fmiEntity.getFoodCategoryEntity().getStoreInfoEntity().getSiName();
        this.cateName = fmiEntity.getFoodCategoryEntity().getFcName();
        this.menuName = fmiEntity.getFmiName();
        this.optionCateName = fmoEntity.getFmoName();
        // this.optionList = fmoEntity.getFdoEntityList();
        List<ShowFoodOptionVO> list = new ArrayList<ShowFoodOptionVO>();
        for (FoodDetailOptionEntity data : fmoEntity.getFdoEntityList()) {
            ShowFoodOptionVO vo = new ShowFoodOptionVO(data);
            list.add(vo);
        }
        this.optionList = list;
    }
}