package com.deliverygig.moonjyoung.vo.store;

import java.time.LocalTime;
import java.util.List;

import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;
import com.deliverygig.moonjyoung.entity.store.StoreDetailInfoEntity;
import com.deliverygig.moonjyoung.vo.delivery.ClosePickupTimeVO;

import lombok.Data;

@Data
public class ShowStoreInfoVO {
    private Long siSeq;
    private String siName;
    private Integer minOrderPrice;
    private Integer deliveryPrice;
    private LocalTime storeCloseTime;
    private LocalTime utiDeliveryTime;
    private String ownerWord;
    private String phoneNumber;
    private String address;
    private String ownerName;
    private String sdiName;
    private String businessNumber;
    private String origin;
    private List<ClosePickupTimeVO> closePickUpTimeList;

    public ShowStoreInfoVO(StoreDetailInfoEntity entity1, StoreTimeDetailEntity entity2) {
        this.siSeq = entity1.getStoreInfoEntity().getSiSeq();
        this.siName = entity1.getStoreInfoEntity().getSiName();
        this.minOrderPrice = entity1.getSdiMinOrderPrice();
        this.deliveryPrice = entity1.getSdiDeliveryPrice();
        this.storeCloseTime = entity2.getStdCloseTime();
        this.utiDeliveryTime = entity2.getUnivTimeInfoEntity().getUtiPickupTime1();
        this.ownerWord = entity1.getSdiOwnerWord();
        this.phoneNumber = entity1.getSdiPhoneNumber();
        this.address = entity1.getSdiAddress();
        this.ownerName = entity1.getSdiOwnerName();
        this.sdiName = entity1.getSdiStoreName();
        this.businessNumber = entity1.getSdiBusinessNumber();
        this.origin = entity1.getSdiOrigin();
    }
}
