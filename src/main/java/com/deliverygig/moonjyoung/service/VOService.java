package com.deliverygig.moonjyoung.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;
import com.deliverygig.moonjyoung.entity.store.StoreDetailInfoEntity;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.delivery.StoreTimeDetailRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivTimeInfoRepository;
import com.deliverygig.moonjyoung.repository.store.StoreDetailInfoRepository;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;
import com.deliverygig.moonjyoung.vo.delivery.ClosePickupTimeVO;
import com.deliverygig.moonjyoung.vo.delivery.LocationListVO;
import com.deliverygig.moonjyoung.vo.delivery.PickUpAreaVO;
import com.deliverygig.moonjyoung.vo.delivery.StoreUnivTimeVO;
import com.deliverygig.moonjyoung.vo.delivery.UnivTimeVO;
import com.deliverygig.moonjyoung.vo.store.StoreDetailInfoVO;
import com.deliverygig.moonjyoung.vo.store.StoreListInfoVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Service
@AllArgsConstructor
@NoArgsConstructor
public class VOService {
    @Autowired UnivInfoRepository univInfoRepository;
    @Autowired PickUpAreaRepository pickUpAreaRepository;
    @Autowired StoreInfoRepository storeInfoRepository;
    @Autowired StoreTimeDetailRepository storeTimeDetailRepository;
    @Autowired UnivTimeInfoRepository univTimeInfoRepository;
    @Autowired StoreDetailInfoRepository storeDetailInfoRepository;

    public Map<String, Object> getLocationList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<LocationListVO> returnList = new ArrayList<LocationListVO>();
        for (PickUpAreaEntity puaData : pickUpAreaRepository.findAll()) {
            LocationListVO vo = new LocationListVO();
            for (UnivTimeInfoEntity data : univTimeInfoRepository.findAll()) {
                if (data.getUnivInfoEntity().getUiSeq()==puaData.getUnivInfoEntity().getUiSeq()) {
                    vo.setUiSeq(data.getUnivInfoEntity().getUiSeq());
                    vo.setUiName(data.getUnivInfoEntity().getUiName());
                    vo.setPuaSeq(puaData.getPuaSeq());
                    vo.setPuaName(puaData.getPuaName());
                }
                else {
                    continue;
                }
                List<UnivTimeVO> timeVOList = getUnivTimeVOList(data.getUnivInfoEntity().getUiSeq());
                vo.setUnivTimeVOList(timeVOList);
            }
            returnList.add(vo);
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("msg", "조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }

    public List<UnivTimeVO> getUnivTimeVOList(Long uiSeq) {
        List<UnivTimeVO> list = new ArrayList<UnivTimeVO>();
        for (UnivTimeInfoEntity data : univTimeInfoRepository.findAll()) {
            UnivTimeVO timeVO = new UnivTimeVO();
            if (uiSeq==data.getUnivInfoEntity().getUiSeq()) {
                timeVO.setUiSeq(data.getUnivInfoEntity().getUiSeq());
                timeVO.setTimeName(data.getUtiName());
                timeVO.setDeliTime1(data.getUtiPickupTime1());
                timeVO.setDeliTime2(data.getUtiPickupTime2());
            }
            else {
                continue;
            }
            list.add(timeVO);
        }
        return list;
    }

    public Map<String, Object> getStoreUnivTime() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<StoreUnivTimeVO> returnList = new ArrayList<StoreUnivTimeVO>();
        
        for (StoreTimeDetailEntity data : storeTimeDetailRepository.findAll()) {
            StoreUnivTimeVO vo = new StoreUnivTimeVO();
            vo.setUiName(data.getUnivTimeInfoEntity().getUnivInfoEntity().getUiName());
            vo.setPuaCloseTime(data.getUnivTimeInfoEntity().getUtiCloseTime());
            vo.setPuaDeliTime1(data.getUnivTimeInfoEntity().getUtiPickupTime1());
            vo.setPuaDeliTime2(data.getUnivTimeInfoEntity().getUtiPickupTime2());
            vo.setSiName(data.getStoreInfoEntity().getSiName());
            vo.setStdCloseTime(data.getStdCloseTime());
            returnList.add(vo);
        }

        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("msg", "배달지역 내 가게 주문시간 조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }

    public Map<String, Object> getPickUpArea() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<PickUpAreaVO> returnList = new ArrayList<PickUpAreaVO>();
        for (PickUpAreaEntity data : pickUpAreaRepository.findAll()) {
            PickUpAreaVO vo = new PickUpAreaVO();
            vo.setUiName(data.getUnivInfoEntity().getUiName());
            vo.setPuaName(data.getPuaName());
            returnList.add(vo);
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("msg", "조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }

    public Map<String, Object> getDeliveryStore() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<StoreListInfoVO> returnList = new ArrayList<StoreListInfoVO>();
        for (StoreTimeDetailEntity data : storeTimeDetailRepository.findAll()) {
            StoreListInfoVO vo = new StoreListInfoVO();
            vo.setSiName(data.getStoreInfoEntity().getSiName());
            vo.setUnivTimeName(data.getUnivTimeInfoEntity().getUtiName());
            vo.setUtiCloseTime(data.getUnivTimeInfoEntity().getUtiCloseTime());
            vo.setUtiPickupTime(data.getUnivTimeInfoEntity().getUtiPickupTime1());
            vo.setSiCloseTime(data.getStdCloseTime());
            vo.setSiDiscount(data.getStoreInfoEntity().getSiDiscount());
            returnList.add(vo);
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("msg", "조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }

    public Map<String, Object> getDeliveryStoreDetail() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<StoreDetailInfoVO> returnList = new ArrayList<StoreDetailInfoVO>();
        for (StoreDetailInfoEntity data : storeDetailInfoRepository.findAll()) {
            StoreDetailInfoVO vo = new StoreDetailInfoVO();
            vo.setSdiOwnerWord(data.getSdiOwnerWord());
            vo.setSdiPhoneNumber(data.getSdiPhoneNumber());
            vo.setSdiAddress(data.getSdiAddress());
            vo.setSdiOwnerName(data.getSdiOwnerName());
            vo.setSdiStoreName(data.getSdiStoreName());
            vo.setSdiBusinessNumber(data.getSdiBusinessNumber());
            vo.setSdiOrigin(data.getSdiOrigin());
            List<ClosePickupTimeVO> list = new ArrayList<ClosePickupTimeVO>();
            for (StoreTimeDetailEntity data2 : storeTimeDetailRepository.findAll()) {
                if (data2.getStoreInfoEntity().getSiSeq()==data.getStoreInfoEntity().getSiSeq()) {
                    ClosePickupTimeVO vo2 = new ClosePickupTimeVO();
                    vo2.setName(data2.getUnivTimeInfoEntity().getUtiName());
                    vo2.setCloseTime(data2.getStdCloseTime());
                    vo2.setPickupTime(data2.getUnivTimeInfoEntity().getUtiPickupTime1());
                    list.add(vo2);
                }
            }
            vo.setClosePickupTimeVoList(list);
            vo.setSdiMinOrderPrice(data.getSdiDeliveryPrice());
            vo.setSdiDeliveryPrice(data.getSdiDeliveryPrice());
            returnList.add(vo);
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("msg", "조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }
}
