package com.deliverygig.moonjyoung.vo.store;

import lombok.Data;

@Data
public class UpdateStoreDetailVO {
    private String sdiOwnerWord;
    private String sdiPhoneNumber;
    private String sdiAddress;
    private String sdiOwnerName;
    private String sdiStoreName;
    private String sdiBusinessNumber;
    private String sdiOrigin;
    private Integer sdiMinOrderPrice;
    private Integer sdiDeliveryPrice;
}
