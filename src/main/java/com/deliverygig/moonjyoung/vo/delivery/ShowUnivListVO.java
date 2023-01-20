package com.deliverygig.moonjyoung.vo.delivery;

import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;

import lombok.Data;

@Data
public class UnivListVO {
    private Long uiSeq;
    private String uiName;

    public UnivListVO(UnivInfoEntity entity) {
        this.uiSeq = entity.getUiSeq();
        this.uiName = entity.getUiName();
    }
}
