package com.deliverygig.moonjyoung.api.storepage;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.account.OwnerEntity;
import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;
import com.deliverygig.moonjyoung.repository.account.OwnerRepository;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;
import com.deliverygig.moonjyoung.vo.store.AddstoreInfoVo;

import jakarta.servlet.http.HttpSession;

@Service
public class StoreService {
    @Autowired OwnerRepository oRepo;
    @Autowired StoreInfoRepository sRepo;

    public Map<String, Object> addStoreInfo(AddstoreInfoVo data, HttpSession session){
         Map<String, Object> map= new LinkedHashMap<String,Object>();
        OwnerEntity loginOwner = (OwnerEntity)session.getAttribute("loginOwner");
        if(data.getSiName() == null || data.getSiName().equals("")){
            map.put("status", false);
            map.put("message", "가게 이름을 입력하세요.");
        }
        else if(sRepo.countBySiName(data.getSiName()) != 0){
            map.put("status", false);
            map.put("message", data.getSiName()+"은/는 이미 존재합니다.");
        }
        else if(data.getSiDiscount() == null){
            StoreInfoEntity entity = new StoreInfoEntity();
            entity.setSiDiscount(entity.getSiDiscount());
        }
        else{
            StoreInfoEntity entity = new StoreInfoEntity();
            entity.setSiName(data.getSiName());
            entity.setSiOiSeq(loginOwner.getOiSeq());
            entity.setSiDiscount(data.getSiDiscount());
            sRepo.save(entity);
            map.put("status", true);
            map.put("message", "가게 기본정보 등록 완료");
        }
        return map;
    }
}
