package com.deliverygig.moonjyoung.vo.mycart;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.deliverygig.moonjyoung.entity.mycart.PickFoodDetailOptionEntity;
import com.deliverygig.moonjyoung.entity.mycart.PickFoodMenuEntity;

import lombok.Data;

@Data
public class FoodBasketVO {
    private Long ciSeq;
    private String fbNumber;
    private String siName;
    private List<FoodBasketMenuOptionVO> menuoptionList;
    private Integer price;
    private String deliveryLocation;
    private LocalTime deliveryTime;

    // public FoodBasketVO(PickFoodDetailOptionEntity entity) {
    //     this.ciSeq = entity.getPickFoodMenuEntity().getFoodBasketEntity().getFbCiSeq();
    //     this.fbNumber = entity.getPickFoodMenuEntity().getFoodBasketEntity().getFbNumber();
    //     this.siName = entity.getPickFoodMenuEntity().getFoodMenuInfoEntity().getFoodCategoryEntity().getStoreInfoEntity().getSiName();
    //     this.deliveryTime = entity.getPickFoodMenuEntity().getFoodBasketEntity().getUnivTimeInfoEntity().getUtiPickupTime1();
    //     // this.price = 계산후 주문시 입력
    //     // this.count = 주문시 입력
    //     // this.deliveryLocation = 주문시 입력
    //     // this.menuoptionList;
    // }
    public FoodBasketVO(PickFoodMenuEntity entity) {
        this.ciSeq = entity.getFoodBasketEntity().getFbCiSeq();
        this.fbNumber = entity.getFoodBasketEntity().getFbNumber();
        this.siName = entity.getFoodMenuInfoEntity().getFoodCategoryEntity().getStoreInfoEntity().getSiName();
        this.deliveryTime = entity.getFoodBasketEntity().getUnivTimeInfoEntity().getUtiPickupTime1();
    }
}
