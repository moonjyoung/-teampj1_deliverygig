package com.deliverygig.moonjyoung.vo.review;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StoreReviewListVO {
    private Long ciSeq;
    private String menu;
    private String menuOption;
    private Integer reviewScore;
    private String reviewContent;
    private LocalDateTime reviewRegDt;
}


