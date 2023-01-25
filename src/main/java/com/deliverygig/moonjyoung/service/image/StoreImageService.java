package com.deliverygig.moonjyoung.service.image;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.image.StoreImageEntity;
import com.deliverygig.moonjyoung.repository.image.StoreImageRepository;

@Service
public class StoreImageService {
    @Autowired StoreImageRepository StRepo;

public Map<String, Object> addStoreImage (StoreImageEntity data) {
    Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
    StRepo.save(data);
    resultMap.put("status", true);
    resultMap.put("message", "파일이 저장되었습니다.");
    resultMap.put("code", HttpStatus.OK);
    return resultMap;
}
}