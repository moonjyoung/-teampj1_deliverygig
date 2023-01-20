package com.deliverygig.moonjyoung.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.mycart.PickFoodDetailOptionEntity;
import com.deliverygig.moonjyoung.entity.mycart.PickFoodMenuEntity;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.mycart.FoodBasketRepository;
import com.deliverygig.moonjyoung.repository.mycart.PickFoodDetailOptionRepository;
import com.deliverygig.moonjyoung.repository.mycart.PickFoodMenuRepository;
import com.deliverygig.moonjyoung.vo.mycart.FoodBasketMenuOptionVO;
import com.deliverygig.moonjyoung.vo.mycart.FoodBasketVO;

@Service
public class FoodBasketService {
    @Autowired FoodBasketRepository foodBasketRepository;
    @Autowired PickFoodMenuRepository pickFoodMenuRepository;
    @Autowired PickFoodDetailOptionRepository pickFoodDetailOptionRepository;
    @Autowired PickUpAreaRepository pickUpAreaRepository;

    public Map<String, Object> getFoodBasket(Long ciSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<FoodBasketVO> returnList = new ArrayList<FoodBasketVO>();

        for (PickFoodMenuEntity data : pickFoodMenuRepository.findAll()) {
            Integer menuPrice = 0;
            Integer optionPrice = 0;
            String tmpOption = "";
            List<FoodBasketMenuOptionVO> list = new ArrayList<FoodBasketMenuOptionVO>();
            FoodBasketMenuOptionVO vo = new FoodBasketMenuOptionVO();
            String tmpMenu = data.getFoodMenuInfoEntity().getFmiName();
            menuPrice = data.getFoodMenuInfoEntity().getFmiPrice();

            for (PickFoodDetailOptionEntity data2 : pickFoodDetailOptionRepository.findAll()) {
                if (data2.getPickFoodMenuEntity().getPfmSeq()==data.getPfmSeq()) {
                    tmpOption += data2.getFoodDetailOptionEntity().getFdoName()+" ";
                    optionPrice += data2.getFoodDetailOptionEntity().getFdoPrice();
                }
                else {
                    continue;
                }
            }
            vo.setMenu(tmpMenu);
            vo.setOption(tmpOption);
            vo.setPrice(optionPrice);
            list.add(vo);
            FoodBasketVO vo2 = new FoodBasketVO(data);
            if (vo2.getCiSeq()!=ciSeq) {
                resultMap.put("status", false);
                resultMap.put("code", HttpStatus.NOT_FOUND);
                resultMap.put("message", "장바구니가 비어있습니다.");
                return resultMap;
            }
            vo2.setMenuoptionList(list);
            data.setPrice(menuPrice+optionPrice);
            vo2.setPrice(menuPrice+optionPrice);
            pickFoodMenuRepository.save(data);
            vo2.setDeliveryLocation("임시테스트"); // 배송지 입력받게 변경
            //     PickUpAreaEntity entity = pickUpAreaRepository.findByPuaSeq(7L);
            //     vo.setDeliveryLocation(entity.getPuaName());
            returnList.add(vo2);
        }

        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("message", "장바구니 조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }
}
