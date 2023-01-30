package com.deliverygig.moonjyoung.api.adminpage;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;

@Service
public class PickUpAreaService {
  @Autowired PickUpAreaRepository pRepository;
  @Autowired UnivInfoRepository univInfoRepository;

  public Map<String, Object> getPickUpList(String keyword, Pageable pageable) {
    Page<PickUpAreaEntity> page = pRepository.findByPuaNameContains(keyword, pageable);
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("list", page.getContent());
    map.put("total", page.getTotalElements());
    map.put("totalpage", page.getTotalPages());
    map.put("currentPage", page.getNumber());
    return map;
  }
  
  public Map<String, Object> addPickUpArea(String univ, String pickUpArea) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    PickUpAreaEntity pickUpAreaEntity = new PickUpAreaEntity();
    UnivInfoEntity data = univInfoRepository.findByUiNameContains(univ);
    Long univ_no = data.getUiSeq();
    if (univInfoRepository.findByUiNameContains(univ) == null) {
        resultMap.put("status", false);
        resultMap.put("message", "존재하지 않는 대학입니다.");
        return resultMap;
    } 
    else if (pickUpArea == null || pickUpArea.equals("")) {
        resultMap.put("status", false);
        resultMap.put("message", "올바른 수령장소를 입력해주세요");
        return resultMap;
    }
    else if(pRepository.findByPuaSeqAndPuaName(univ_no, pickUpArea) != null) {
        resultMap.put("status", false);
        resultMap.put("message", "이미 존재하는 수령장소입니다.");
        return resultMap;
    }
    else {
        UnivInfoEntity entity = univInfoRepository.findByUiNameContains(univ);
        pRepository.save(pickUpAreaEntity.builder().puaSeq(null).puaName(pickUpArea).univInfoEntity(entity).build());
    }
    resultMap.put("status", true);
    resultMap.put("message", "새 수령장소 추가 성공");
    return resultMap;
  }
}
