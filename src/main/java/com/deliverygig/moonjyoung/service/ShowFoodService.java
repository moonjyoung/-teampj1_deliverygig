package com.deliverygig.moonjyoung.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.food.FoodCategoryEntity;
import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;
import com.deliverygig.moonjyoung.entity.food.FoodOptionConnectEntity;
import com.deliverygig.moonjyoung.repository.food.FoodCategoryRepository;
import com.deliverygig.moonjyoung.repository.food.FoodDetailOptionRepository;
import com.deliverygig.moonjyoung.repository.food.FoodMenuInfoRepository;
import com.deliverygig.moonjyoung.repository.food.FoodOptionConnectRepository;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;
import com.deliverygig.moonjyoung.vo.food.ShowFoodListVO;
import com.deliverygig.moonjyoung.vo.food.ShowMenuInfoVO;

import lombok.Data;

@Data
@Service
public class ShowFoodService {
    @Autowired StoreInfoRepository storeInfoRepository;
    @Autowired FoodCategoryRepository foodCategoryRepository;
    @Autowired FoodMenuInfoRepository foodMenuInfoRepository;
    @Autowired FoodDetailOptionRepository foodDetailOptionRepository;
    @Autowired FoodOptionConnectRepository foodOptionConnectRepository;

    public Map<String, Object> getMenuList(Long siSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowFoodListVO> returnList = new ArrayList<ShowFoodListVO>();
        String storeName = "";
                
        if (storeInfoRepository.findById(siSeq).isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("message", "존재하지 않는 가게입니다.");
        }
        else if (foodCategoryRepository.findAllBySiSeq(siSeq).size()==0) {
            resultMap.put("status", true);
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("message", "조회 완료(가게에 등록된 메뉴가 없습니다)");
            resultMap.put("store", storeInfoRepository.findBySiSeq(siSeq).getSiName());
            return resultMap;
        }
        else {
            for (FoodCategoryEntity data2 : foodCategoryRepository.findAllBySiSeq(siSeq)) {
                ShowFoodListVO vo = new ShowFoodListVO();
                List<ShowMenuInfoVO> menuList = new ArrayList<ShowMenuInfoVO>();
                if (data2.getStoreInfoEntity().getSiSeq()==siSeq) {
                    storeName = data2.getStoreInfoEntity().getSiName();
                    vo.setCateName(data2.getFcName()+" "+foodMenuInfoRepository.findAllByFmiFcSeq(data2.getFcSeq()).size()+"개");
                    for (FoodMenuInfoEntity data : foodMenuInfoRepository.findAllByFmiFcSeq(data2.getFcSeq())) {
                        ShowMenuInfoVO vo2 = new ShowMenuInfoVO(data);
                        menuList.add(vo2);
                        // menuList.add(new ShowMenuInfoVO(data));
                    }
                    vo.setMenuList(menuList);
                }
                returnList.add(vo);
            }
            resultMap.put("status", true);
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("message", "조회 완료");
            resultMap.put("store", storeName);
            resultMap.put("list", returnList);
        }
        
        return resultMap;
    }
}
