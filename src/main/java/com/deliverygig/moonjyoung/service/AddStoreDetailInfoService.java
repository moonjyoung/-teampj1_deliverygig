package com.deliverygig.moonjyoung.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

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

    public Map<String, Object> addStoreDetail(StoreDetailInfoVO data,Long seq, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        OwnerEntity data2 = (OwnerEntity) session.getAttribute("loginOwner");
        boolean status = true;
        if (session.getAttribute("loginOwner") == null) {
            resultMap.put("message", "로그인 먼저 해주세요.");
            status = false;
        }
        if (sRepo.findBySiSeq(seq) == null) {
                resultMap.put("message", "가게 정보가 없습니다.");
                status = false;
            }
            StoreDetailInfoEntity entity = dRepo.findBySdiSeq(seq);
            // StoreInfoEntity entity2 = sRepo.findBySiSeq(sdiSeq);
        if (!entity.getStoreInfoEntity().getSiOiSeq().equals(data2.getOiSeq())) {
            resultMap.put("message", "해당 회원의 가게가 아닙니다.");
            status = false;
        }
        String phone_pattern = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
        Pattern p = Pattern.compile(phone_pattern);
        if (data.getSdiMinOrderPrice() < 0 || data.getSdiDeliveryPrice() < 0) {
            status = false;
            resultMap.put("message", "알맞지 않은 최소주문금액이거나 배달비금액 입니다.");
        }
        if (data.getSdiDeliveryPrice() == null || data.getSdiMinOrderPrice() == null) {
                status = false;
                resultMap.put("message", "최소주문금액이나 배달비는 반드시 작성해주셔야 합니다.");
        }
        if (!p.matcher(data.getSdiPhoneNumber()).matches()) {
            status = false;
            resultMap.put("message", "알맞지 않은 전화번호입니다.");
        }
        if (!status) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        entity.setSdiDeliveryPrice(data.getSdiDeliveryPrice()); // 배달비
        entity.setSdiMinOrderPrice(data.getSdiMinOrderPrice()); // 최소주문금액
        entity.setSdiOwnerWord(data.getSdiOwnerWord()); // 사장님 한마디
        entity.getStoreInfoEntity().setSiSeq(seq); // 가게 번호
        entity.setSdiPhoneNumber(data.getSdiPhoneNumber()); // 폰 번호
        entity.setSdiAddress(data.getSdiAddress()); // 주소
        entity.setSdiOrigin(data.getSdiOrigin()); // 원산지 
        entity.setSdiOwnerName(data.getSdiOwnerName()); // 사업자 이름
        entity.setSdiStoreName(data.getSdiStoreName()); // 가게  상호명
        entity.setSdiBusinessNumber(data.getSdiAddress()); // 사업자 번호
        dRepo.save(entity);
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.CREATED);
        return resultMap;
    }
}
