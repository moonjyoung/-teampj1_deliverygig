package com.deliverygig.moonjyoung.vo.delivery;

import java.time.LocalTime;

import lombok.Data;

@Data
public class ClosePickupTimeVO {
    private String name;
    private LocalTime closeTime;
    private LocalTime pickupTime;
}
