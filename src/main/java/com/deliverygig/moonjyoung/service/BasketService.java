package com.deliverygig.moonjyoung.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.mycart.BasketInfoEntity;
import com.deliverygig.moonjyoung.entity.mycart.BasketMenuOptionsCombineEntity;
import com.deliverygig.moonjyoung.repository.account.CustomerRepository;
import com.deliverygig.moonjyoung.repository.delivery.StoreTimeDetailRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivTimeInfoRepository;
import com.deliverygig.moonjyoung.repository.food.FoodDetailOptionRepository;
import com.deliverygig.moonjyoung.repository.food.FoodMenuInfoRepository;
import com.deliverygig.moonjyoung.repository.food.FoodOptionConnectRepository;
import com.deliverygig.moonjyoung.repository.mycart.BasketInfoRepository;
import com.deliverygig.moonjyoung.repository.mycart.BasketMenuOptionsCombineRepository;
import com.deliverygig.moonjyoung.vo.food.ShowFoodDetailOptionVO;
import com.deliverygig.moonjyoung.vo.food.ShowMenuDetailVO;
import com.deliverygig.moonjyoung.vo.mycart.AddBasketMenuOptionVO;
import com.deliverygig.moonjyoung.vo.mycart.GetBasketMenuOptionVO;
import com.deliverygig.moonjyoung.vo.mycart.ShowBasketVO;

@Service
public class BasketService {
    @Autowired UnivTimeInfoRepository univTimeInfoRepository;
    @Autowired StoreTimeDetailRepository storeTimeDetailRepository;
    @Autowired FoodMenuInfoRepository foodMenuInfoRepository;
    @Autowired FoodOptionConnectRepository foodOptionConnectRepository;
    @Autowired FoodDetailOptionRepository foodDetailOptionRepository;
    @Autowired CustomerRepository customerRepository;
    @Autowired BasketInfoRepository basketInfoRepository;
    @Autowired BasketMenuOptionsCombineRepository basketMenuOptionsCombineRepository;

    public Map<String, Object> getBasketInfo(Long ciSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        if (basketInfoRepository.findByBiCiSeqAndBiStatus(ciSeq, 1)==null) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 장바구니입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            ShowBasketVO vo = new ShowBasketVO(basketInfoRepository.findByBiCiSeqAndBiStatus(ciSeq, 1));
            resultMap.put("status", true);
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("data", vo);
        }
        return resultMap;
    }

    public Map<String, Object> getMenuOptions(AddBasketMenuOptionVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        GetBasketMenuOptionVO vo = new GetBasketMenuOptionVO();
        // Long fmiSeq = data.getBfVO().getSeq();
        Long fmiSeq = data.getFmiSeq();
        Integer price = (int)(foodMenuInfoRepository.findByFmiSeq(fmiSeq).getFmiPrice()*(1-foodMenuInfoRepository.findByFmiSeq(fmiSeq).getFoodCategoryEntity().getStoreInfoEntity().getSiDiscount()));
        
        vo.setDeliveryTime(univTimeInfoRepository.findByUtiSeq(data.getUtiSeq()).getUtiPickupTime1());
        vo.setMenuName(foodMenuInfoRepository.findByFmiSeq(fmiSeq).getFmiName());

        List<ShowFoodDetailOptionVO> sfdoVOList = new ArrayList<ShowFoodDetailOptionVO>();
        for (int i=0; i<data.getFdoSeqList().size(); i++) {
            sfdoVOList.add(new ShowFoodDetailOptionVO(foodDetailOptionRepository.findById(data.getFdoSeqList().get(i)).get()));
        }
        vo.setOptionAll(getOptionName(fmiSeq, sfdoVOList));
        price += getOptionPrice(fmiSeq, sfdoVOList);
        vo.setPrice(price);

        Long biSeq = 0L;
        if  (basketInfoRepository.findByBiCiSeqAndBiStatus(data.getCiSeq(), 1)==null) {
            // biSeq = new BasketInfoEntity().getBiSeq();
        }
        else {
            biSeq = basketInfoRepository.findByBiCiSeqAndBiStatus(data.getCiSeq(), 1).getBiSeq();
        }
        
        
        BasketMenuOptionsCombineEntity entity = new BasketMenuOptionsCombineEntity(biSeq, data.getUtiSeq(), data.getFmiSeq(), vo.getOptionAll(), vo.getPrice());

        basketMenuOptionsCombineRepository.save(entity);

        resultMap.put("status", true);
        resultMap.put("message", "추가 완료");
        resultMap.put("code", HttpStatus.ACCEPTED);
        resultMap.put("data", vo);
        return resultMap;
    }

    public String getOptionName(Long fmiSeq, List<ShowFoodDetailOptionVO> sfdoVOList) {
        String optionAll = "";
        if (foodOptionConnectRepository.findAllByFocFmiSeq(fmiSeq).size()!=0) {
            for (ShowFoodDetailOptionVO data : sfdoVOList) {
                optionAll += data.getOptionName()+" ";
            }
        }
        return optionAll;
    }

    public Integer getOptionPrice(Long fmiSeq, List<ShowFoodDetailOptionVO> sfdoVOList) {
        Integer price = 0;
        if (foodOptionConnectRepository.findAllByFocFmiSeq(fmiSeq).size()!=0) {
            for (ShowFoodDetailOptionVO data : sfdoVOList) {
                price += data.getOptionPrice();
            }
        }
        return price;
    }
}
