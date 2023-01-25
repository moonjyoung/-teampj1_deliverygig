package com.deliverygig.moonjyoung.service.image;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.image.PickUpAreaImageEntity;
import com.deliverygig.moonjyoung.repository.image.PickUpAreaImageRepository;

@Service
public class PickUpAreaImageService {
    @Autowired PickUpAreaImageRepository PiRepo;

public Map<String, Object> addPickUpAreaImage(PickUpAreaImageEntity data) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    PiRepo.save(data);
    resultMap.put("status", true);
    resultMap.put("message", "파일이 저장되었습니다.");
    resultMap.put("code", HttpStatus.OK);
    return resultMap;
  }
}