package com.deliverygig.moonjyoung.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.FoodDetailOptionCheckEntity;
import com.deliverygig.moonjyoung.repository.FoodBasketRepository;
import com.deliverygig.moonjyoung.repository.FoodDetailOptionCheckRepository;
import com.deliverygig.moonjyoung.vo.FoodBasketVO;

@Service
public class FoodBasketService {
    @Autowired FoodBasketRepository foodBasketRepository;
    @Autowired FoodDetailOptionCheckRepository foodDetailOptionCheckRepository;

    public Map<String, Object> getFoodBasket(Long ciSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<FoodBasketVO> returnList = new ArrayList<FoodBasketVO>();
        Integer optionPrice = 0;
        FoodBasketVO vo = new FoodBasketVO();
        for (FoodDetailOptionCheckEntity data : foodDetailOptionCheckRepository.findByFdocCiSeq(ciSeq)) {
            optionPrice = 0;
            List<String> optionNameList = new ArrayList<String>();
            // optionNameList.clear();
            vo.setCiSeq(ciSeq);
            // vo.setMenuName(data.getFoodDetailOptionEntity().getFoodMenuOptionEntity().getFoodMenuInfoEntity().getFmiName());
            // vo.setMenuPrice(data.getFoodDetailOptionEntity().getFoodMenuOptionEntity().getFoodMenuInfoEntity().getFmiPrice());
            // for (FoodDetailOptionCheckEntity data2 : foodDetailOptionCheckRepository.findByFdocCiSeq(ciSeq)) {
            //     if (data2.getFoodDetailOptionEntity().getFoodMenuOptionEntity().getFoodMenuInfoEntity().getFmiSeq()==data.getFoodDetailOptionEntity().getFoodMenuOptionEntity().getFoodMenuInfoEntity().getFmiSeq()) {
            //         optionPrice = optionPrice+data2.getFoodDetailOptionEntity().getFdoPrice();
            //         optionNameList.add(data2.getFoodDetailOptionEntity().getFdoName());
            //     }
            // }
            vo.setOptionPrice(optionPrice);
            vo.setMenuOptionList(optionNameList);
            vo.setTotalPrice(optionPrice+vo.getMenuPrice());
            vo.setCount(1); // 메뉴 구매 갯수 어디다가 추가할까?
        }
        returnList.add(vo);
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("msg", "장바구니 조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }
}
