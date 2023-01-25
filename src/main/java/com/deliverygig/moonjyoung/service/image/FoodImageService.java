package com.deliverygig.moonjyoung.service.image;

import java.util.LinkedHashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.image.FoodImageEntity;
import com.deliverygig.moonjyoung.repository.image.FoodImageRepository;



@Service
public class FoodImageService {
    @Autowired FoodImageRepository FoRepo;
public Map<String, Object> addFoodImage(FoodImageEntity data) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    FoRepo.save(data);
    resultMap.put("status", true);
    resultMap.put("message", "파일이 저장되었습니다.");
    resultMap.put("code", HttpStatus.OK);
    return resultMap;
  }
}
