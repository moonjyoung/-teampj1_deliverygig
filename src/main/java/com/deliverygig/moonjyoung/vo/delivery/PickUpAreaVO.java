package com.deliverygig.moonjyoung.vo.delivery;

import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;

import lombok.Data;

@Data
public class PickUpAreaVO {
    private String uiName;
    private String puaName;

    public UnivInfoEntity toUnivInfoEntity() {
        return new UnivInfoEntity(null, uiName, null);
    }
}
