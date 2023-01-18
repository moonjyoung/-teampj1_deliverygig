package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverygig.moonjyoung.service.AddStoreDetailInfoService;
import com.deliverygig.moonjyoung.service.AddstoreInfoService;
import com.deliverygig.moonjyoung.service.VOService;
import com.deliverygig.moonjyoung.vo.store.AddstoreInfoVo;
import com.deliverygig.moonjyoung.vo.store.UpdateStoreDetailVO;
import com.deliverygig.moonjyoung.vo.store.UpdateStoreVO;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    VOService voService;
    @Autowired
    AddstoreInfoService aService;
    @Autowired
    AddStoreDetailInfoService dService;
     
    // 배달 장소/시간대별 가게 목록
    @GetMapping("/list")
    public ResponseEntity<Object> getDeliveryStoreList() {
        Map<String, Object> resultMap = voService.getDeliveryStore();
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    // 가게 정보 - 상세 목록 
    @GetMapping("/detaillist")
    public ResponseEntity<Object> getStoreDetailList() {
        Map<String, Object> resultMap = voService.getDeliveryStoreDetail();
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    // 가게 기본정보 - 등록 
    @PutMapping("/add")
    public ResponseEntity<Object> getAddStore(@RequestBody AddstoreInfoVo data, HttpSession session) {
        Map<String, Object> resultMap = aService.addStore(data, session);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    // 가게 기본정보 - 수정
    @PatchMapping("/update/{type}")
    public ResponseEntity<Object> updateStoreInfo(HttpSession session, @PathVariable String type,
            @RequestBody UpdateStoreVO data, @RequestParam Long siSeq) {
        Map<String, Object> resultMap = aService.UpdateStore(data, siSeq, type, session);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    @PatchMapping("/update/{type}")
    public ResponseEntity<Object> updateStoreDetailInfo(HttpSession session, @PathVariable String type,
            @RequestParam Long siSeq, @RequestBody UpdateStoreDetailVO data) {
        Map<String, Object> resultMap = dService.addStoreDetail(data, type, siSeq, session);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    
    
}
