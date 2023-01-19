package com.deliverygig.moonjyoung.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.food.FoodOptionConnectEntity;
import com.deliverygig.moonjyoung.repository.food.FoodDetailOptionRepository;
import com.deliverygig.moonjyoung.repository.food.FoodOptionConnectRepository;
import com.deliverygig.moonjyoung.vo.food.ShowFoodListVO;

import lombok.Data;

@Data
@Service
public class ShowFoodService {
    @Autowired FoodDetailOptionRepository foodDetailOptionRepository;
    @Autowired FoodOptionConnectRepository foodOptionConnectRepository;

    public Map<String, Object> getMenuList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowFoodListVO> returnList = new ArrayList<ShowFoodListVO>();
        
        // for (FoodDetailOptionEntity data : foodDetailOptionRepository.findAll()) {
        //     ShowFoodListVO vo = new ShowFoodListVO();
        //     List<Map<Long, String>> optionList = new ArrayList<Map<Long, String>>();
        //     vo.setSiName(data.getFoodMenuOptionEntity().getFoodMenuInfoEntity().getFoodCategoryEntity().getStoreInfoEntity().getSiName());
        //     vo.setCateName(data.getFoodMenuOptionEntity().getFoodMenuInfoEntity().getFoodCategoryEntity().getFcName());
        //     vo.setMenuName(data.getFoodMenuOptionEntity().getFoodMenuInfoEntity().getFmiName());
        //     vo.setOptionCateName(data.getFoodMenuOptionEntity().getFmoName());
        //     if (returnList.size()>0 && returnList.get(returnList.size()-1).getOptionCateName().equals(vo.getOptionCateName())) {
        //         continue;
        //     }
        //     optionList = getOptionList(data.getFoodMenuOptionEntity().getFmoSeq());
        //     vo.setOptionList(optionList);
        //     returnList.add(vo);
        // }
        for (FoodOptionConnectEntity data : foodOptionConnectRepository.findAll()) {
            ShowFoodListVO vo = new ShowFoodListVO(data.getFoodMenuInfoEntity(), data.getFoodMenuOptionEntity());
            returnList.add(vo);
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("msg", "메뉴 조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }

    // public List<Map<Long, String>> getOptionList(Long seq) {
    //     List<Map<Long, String>> optionList = new ArrayList<Map<Long, String>>();
    //     Map<Long, String> map = new LinkedHashMap<Long, String>();
    //     for (FoodDetailOptionEntity entity : foodDetailOptionRepository.findByFdoFmoSeq(seq)) {
    //         map.put(entity.getFdoSeq(), entity.getFdoName());
    //     }
    //     optionList.add(map);
    //     return optionList;
    // }
}
