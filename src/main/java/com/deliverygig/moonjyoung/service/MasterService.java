package com.deliverygig.moonjyoung.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;
import com.deliverygig.moonjyoung.vo.delivery.PickUpAreaVO;

@Service
public class MasterService {
    @Autowired PickUpAreaRepository pickUpAreaRepository;
    @Autowired UnivInfoRepository univInfoRepository;

    public Map<String, Object> addUniv(String name) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        PickUpAreaVO vo = new PickUpAreaVO();
        vo.setUiName(name);
        // vo.setPuaName("("+name+")수령장소");

        if (name==null || name.equals("")) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("msg", "올바른 대학명을 입력해주세요.");
            return resultMap;
        }
        else if (univInfoRepository.countByUiName(name)!=0) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("msg", "이미 있는 대학명입니다.");
            return resultMap;
        }
        else {
            univInfoRepository.save(vo.toUnivInfoEntity());
            resultMap.put("status", true);
            resultMap.put("code", HttpStatus.CREATED);
            resultMap.put("msg", "새 대학 추가 성공");
        }
        
        return resultMap;
    }

    public Map<String, Object> addPickUpArea(String univ, String pickUpArea) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        PickUpAreaEntity pickUpAreaEntity = new PickUpAreaEntity();
        if (univInfoRepository.findByUiName(univ)==null) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("msg", "존재하지 않는 대학입니다.");
            return resultMap;
        }
        else {
            UnivInfoEntity entity = univInfoRepository.findByUiName(univ);
            pickUpAreaRepository.save(pickUpAreaEntity.builder().puaSeq(null).puaName(pickUpArea).univInfoEntity(entity).build());
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.CREATED);
        resultMap.put("msg", "새 수령장소 추가 성공");
        return resultMap;
    }
}
