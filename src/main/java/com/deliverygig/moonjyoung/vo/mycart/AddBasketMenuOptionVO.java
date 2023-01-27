package com.deliverygig.moonjyoung.vo.mycart;

import java.util.List;

import com.deliverygig.moonjyoung.vo.food.ShowFoodDetailOptionVO;

import lombok.Data;

@Data
public class AddBasketMenuOptionVO {
    private Long ciSeq;
    private Long utiSeq;
    private Long fmiSeq;
    private List<Long> fdoSeqList;
    // private List<ShowFoodDetailOptionVO> sfdoVOList;
}
