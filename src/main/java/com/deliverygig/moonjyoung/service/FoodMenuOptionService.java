package com.deliverygig.moonjyoung.service;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.deliverygig.moonjyoung.entity.food.FoodDetailOptionEntity;
import com.deliverygig.moonjyoung.entity.food.FoodMenuOptionEntity;
import com.deliverygig.moonjyoung.entity.food.FoodOptionConnectEntity;
import com.deliverygig.moonjyoung.repository.food.FoodDetailOptionRepository;
import com.deliverygig.moonjyoung.repository.food.FoodMenuInfoRepository;
import com.deliverygig.moonjyoung.repository.food.FoodMenuOptionRepository;
import com.deliverygig.moonjyoung.repository.food.FoodOptionConnectRepository;
@Service
public class FoodMenuOptionService {
    @Autowired
    FoodMenuOptionRepository menuOptionRepo;
    @Autowired
    FoodDetailOptionRepository optionDetailRepo;

    @Autowired
    FoodMenuInfoRepository menuRepo;

    @Autowired
    FoodOptionConnectRepository optConnectRepo;

    // 가게 카테 옵션 등록 
    public Map<String, Object> addFoodMenuOption(String menucate, Integer duplicated, Integer required) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        FoodMenuOptionEntity entity = new FoodMenuOptionEntity();
        entity.setFmoName(menucate);
        entity.setFmoDuplicated(duplicated);
        entity.setFmoRequiredOption(required);
        menuOptionRepo.save(entity);
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.CREATED);
        resultMap.put("message", "메뉴옵션카테고리정보가 등록되었습니다.");
        return resultMap;
    }
    //  가게 카테 옵션 수정 
    public Map<String, Object> updateFoodMenuOption(Long fmo_seq, String menucate, Integer duplicated, Integer required) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (menuOptionRepo.countByFmoSeq(fmo_seq) == 0) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            resultMap.put("message", "알맞지 않은 메뉴 카테고리 옵션 번호입니다.");
            return resultMap;
        }
        FoodMenuOptionEntity entity = menuOptionRepo.findById(fmo_seq).get();
        if (menucate == null) {
            entity.setFmoName(entity.getFmoName());
        }
        else {
            entity.setFmoName(menucate);
        }
        if (duplicated == null) {
            entity.setFmoDuplicated(entity.getFmoDuplicated());
        }
        else {
            entity.setFmoDuplicated(duplicated);
        }
        if (required == null) {
            entity.setFmoRequiredOption(entity.getFmoRequiredOption());
        }
        else {
            entity.setFmoRequiredOption(required);
        }
        entity.setFmoSeq(fmo_seq);
        menuOptionRepo.save(entity);
        resultMap.put("code", HttpStatus.CREATED);
        resultMap.put("message", "메뉴옵션카테고리정보가 수정되었습니다.");
        return resultMap;
        }
    

    //  메뉴 카테 디테일 옵션 등록 
    public Map<String, Object> addFoodMenuOptionDetail(Long fmo_seq, String name, Integer price, Integer order) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        FoodDetailOptionEntity entity = new FoodDetailOptionEntity();
        if (menuOptionRepo.countByFmoSeq(fmo_seq) == 0) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            resultMap.put("message", "해당 메뉴 카테고리 정보가 없습니다.");
            return resultMap;
        }

        entity.setFoodMenuOptionEntity(menuOptionRepo.findById(fmo_seq).get());
        entity.setFdoName(name);
        entity.setFdoPrice(price);
        entity.setFdoOrder(order);
        optionDetailRepo.save(entity);
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.CREATED);
        resultMap.put("message", "메뉴카테옵션디테일 정보가 등록되었습니다.");
        return resultMap;
    }
    
    // 메뉴 카테 디테일 옵션 수정 
    // public Map(String, Object) updateMenuOptionDetail(Long fdo_seq, String name, Integer price, Integer order){

    // }
    
    // 옵션 연결 테이블 등록 
    public Map<String, Object> addFoodOptionTableConnet(Long foc_fmi_seq, Long foc_fmo_seq, Integer foc_fmo_order) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        FoodOptionConnectEntity entity = new FoodOptionConnectEntity();
        boolean status = true;
        if (optConnectRepo.countByFocFmiSeqAndFocFmoSeq(foc_fmi_seq, foc_fmo_seq) == 1) {
            status = false;
            resultMap.put("message", "해당메뉴에 해당 옵션이 이미 있습니다.");
        }
        else if(menuRepo.countByFmiSeq(foc_fmi_seq) == 0) {
            status = false;
            resultMap.put("status", false);
            resultMap.put("message", "알맞지 않은 메뉴 번호 입니다. ");
        }
        else if(menuOptionRepo.countByFmoSeq(foc_fmo_seq) == 0){
            status = false;
            resultMap.put("status", false);
            resultMap.put("message", "알맞지 않은 메뉴옵션 번호 입니다. ");
        }
        if(!status){
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        entity.setFoodMenuInfoEntity(menuRepo.findByFmiSeq(foc_fmi_seq));
        entity.setFoodMenuOptionEntity(menuOptionRepo.findById(foc_fmo_seq).get());
        entity.setFocFmoOrder(foc_fmo_order);
        optConnectRepo.save(entity);
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.CREATED);
        resultMap.put("message", "메뉴카테옵션연결 정보가 등록되었습니다.");
        return resultMap; 
    }
}
