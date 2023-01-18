package com.deliverygig.moonjyoung.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.account.OwnerEntity;
import com.deliverygig.moonjyoung.entity.store.StoreDetailInfoEntity;
import com.deliverygig.moonjyoung.repository.store.StoreDetailInfoRepository;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;
import com.deliverygig.moonjyoung.vo.store.StoreDetailInfoVO;

import jakarta.servlet.http.HttpSession;

@Service
public class AddStoreDetailInfoService {
    @Autowired
    StoreDetailInfoRepository dRepo;
    @Autowired
    StoreInfoRepository sRepo;

    public Map<String, Object> addStoreDetail(StoreDetailInfoVO data, String type, Long siSeq, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        OwnerEntity data2 = (OwnerEntity) session.getAttribute("loginOwner");
        boolean status = true;
        if (session.getAttribute("loginOwner") == null) {
            resultMap.put("message", "로그인 먼저 해주세요.");
            status = false;
        }
        if (sRepo.findBySiSeq(siSeq) == null) {
                resultMap.put("message", "가게 정보가 없습니다.");
                status = false;
            }
        StoreDetailInfoEntity entity = dRepo.findBySiSeq(siSeq);
        if (!entity.getSiOiSeq().equals(data2.getOiSeq())) {
            resultMap.put("message", "해당 회원의 가게가 아닙니다.");
            status = false;
        }
        
        if (type.equals("detailInfo")) {
            if (data.getSdiMinOrderPrice() < 0 || data.getSdiDeliveryPrice() < 0) {
                status = false;
                resultMap.put("message", "알맞지 않은 최소주문금액이거나 배달비금액 입니다.");
            }
            
            entity.setSdiDeliveryPrice(data.getSdiDeliveryPrice());
            entity.setSdiMinOrderPrice(data.getSdiMinOrderPrice());

            

            if (!status) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
            }
        }
            
        
        if (type.equals("businessInfo")) {

        }
        
        return resultMap;
    }
}
