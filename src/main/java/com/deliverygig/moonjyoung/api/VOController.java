package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverygig.moonjyoung.service.VOService;


@RestController
public class VOController {
    @Autowired VOService voService;

    // 배달 장소 & 시간대 정보 목록
    @GetMapping("/location/list")
    public ResponseEntity<Object> getUnivPickUpAreaTime() {
        Map<String, Object> resultMap = voService.getLocationList();
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    // @GetMapping("/store/list")
    // public ResponseEntity<Object> getStoreUnivTime() {
    //     Map<String, Object> resultMap = voService.getStoreUnivTime();
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    // }
    // @GetMapping("/pickup/list")
    // public ResponseEntity<Object> getUnivPickUpArea() {
    //     Map<String, Object> resultMap = voService.getPickUpArea();
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    // }
   
}
