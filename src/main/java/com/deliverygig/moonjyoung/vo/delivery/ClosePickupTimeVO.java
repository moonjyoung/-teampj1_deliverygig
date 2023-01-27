package com.deliverygig.moonjyoung.vo.delivery;

import java.time.LocalTime;

import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;

import lombok.Data;

// 가게 상세정보 배달시간 목록에 사용 by 문주영
@Data
public class ClosePickupTimeVO {
    private Long utiSeq;
    private String name;
    private LocalTime closeTime;
    private LocalTime pickupTime;
    private Boolean thisTime;

    public ClosePickupTimeVO(StoreTimeDetailEntity entity) {
        this.utiSeq = entity.getUnivTimeInfoEntity().getUtiSeq();
        this.name = entity.getUnivTimeInfoEntity().getUtiName();
        this.closeTime = entity.getStdCloseTime();
        this.pickupTime = entity.getUnivTimeInfoEntity().getUtiPickupTime1();
        this.thisTime = false;
    }
}
