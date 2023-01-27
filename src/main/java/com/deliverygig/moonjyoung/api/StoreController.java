package com.deliverygig.moonjyoung.api;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverygig.moonjyoung.service.AddStoreDetailInfoService;
import com.deliverygig.moonjyoung.service.AddstoreInfoService;
import com.deliverygig.moonjyoung.service.VOService;
import com.deliverygig.moonjyoung.vo.store.AddstoreInfoVo;
import com.deliverygig.moonjyoung.vo.store.StoreDetailInfoVO;
import com.deliverygig.moonjyoung.vo.store.UpdateStoreDetailVO;
import com.deliverygig.moonjyoung.vo.store.UpdateStoreVO;

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
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    // 가게 정보 - 상세 목록 
    @GetMapping("/detaillist")
    public ResponseEntity<Object> getStoreDetailList() {
        Map<String, Object> resultMap = voService.getDeliveryStoreDetail();
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    // 가게 기본정보 - 등록 
    @PostMapping("/add")
    public ResponseEntity<Object> postAddStore(@RequestBody AddstoreInfoVo data) {
        Map<String, Object> resultMap = aService.addStore(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    // 가게 기본정보 - 수정
    @PostMapping("/update/{type}")
    public ResponseEntity<Object> postUpdateStoreInfo(@PathVariable String type,
            @RequestBody UpdateStoreVO data, @RequestParam Long siSeq) {
        Map<String, Object> resultMap = aService.UpdateStore(data, siSeq, type);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    // 디테일 등록
    @PostMapping("/add/detail")
    public ResponseEntity<Object> postAddStoreDetail(@RequestBody StoreDetailInfoVO data, @RequestParam Long siSeq) {
        Map<String, Object> resultMap = dService.addStoreDetail(data, siSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    // 디테일 수정
    @PostMapping("/update/detail")
    public ResponseEntity<Object> postUdateStoreDetail(@RequestBody UpdateStoreDetailVO data,
            @RequestParam Long siSeq) {
        Map<String, Object> resultMap = dService.updateStoreDetail(data, siSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    @PostMapping("/add/closedDay")
    public ResponseEntity<Object> postAddClosedDay(@RequestParam Long seq, @RequestParam String value) {
        Map<String, Object> resultMap = dService.addStoreClosedDay(value, seq);
         return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

 
    
}
