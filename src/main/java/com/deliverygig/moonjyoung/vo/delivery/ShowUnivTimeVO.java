package com.deliverygig.moonjyoung.vo.delivery;

import java.time.LocalTime;

import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;

import lombok.Data;

@Data
public class ShowUnivTimeVO {
    private Long utiSeq;
    private String utiName;
    private LocalTime utiCloseTime;
    private LocalTime utiDeliveryTime;

    public ShowUnivTimeVO(UnivTimeInfoEntity entity) {
        this.utiSeq = entity.getUtiSeq();
        this.utiName = entity.getUtiName();
        this.utiCloseTime = entity.getUtiCloseTime();
        this.utiDeliveryTime = entity.getUtiPickupTime1();
    }
}
