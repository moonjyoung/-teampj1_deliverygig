package com.deliverygig.moonjyoung.vo.store;

import java.time.LocalTime;

import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;

import lombok.Data;

@Data
public class ShowStoreListVO {
    private Long storeSeq;
    private String storeName;
    private LocalTime storeCloseTime;
    private Integer discount;
    private Integer storeStatus;
    // private List<StoreClosedDayInfoVO> storeClosedDay ; 
    //private Long simgSiSeq; 이것은 seq로 받은 join임
    private String simgUriLogo;

    public ShowStoreListVO(StoreTimeDetailEntity entity) {
        this.storeSeq = entity.getStoreInfoEntity().getSiSeq();
        this.storeName = entity.getStoreInfoEntity().getSiName();
        this.storeCloseTime = entity.getStdCloseTime();
        this.discount = entity.getStoreInfoEntity().getSiDiscount();
        this.storeStatus = entity.getStoreInfoEntity().getSiStatus();

    }

    public Integer getdiscount() {
        return discount;
    }

    public ShowStoreListVO() {
    }

    
    
}
