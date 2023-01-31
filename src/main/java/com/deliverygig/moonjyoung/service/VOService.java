package com.deliverygig.moonjyoung.service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;
import com.deliverygig.moonjyoung.entity.food.FoodDetailOptionEntity;
import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;
import com.deliverygig.moonjyoung.entity.food.FoodMenuOptionEntity;
import com.deliverygig.moonjyoung.entity.food.FoodOptionConnectEntity;
import com.deliverygig.moonjyoung.entity.image.StoreImageEntity;
import com.deliverygig.moonjyoung.entity.store.StoreClosedDayEntity;
import com.deliverygig.moonjyoung.entity.store.StoreDetailInfoEntity;
import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.delivery.StoreTimeDetailRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivTimeInfoRepository;
import com.deliverygig.moonjyoung.repository.food.FoodDetailOptionRepository;
import com.deliverygig.moonjyoung.repository.food.FoodMenuInfoRepository;
import com.deliverygig.moonjyoung.repository.food.FoodMenuOptionRepository;
import com.deliverygig.moonjyoung.repository.food.FoodOptionConnectRepository;
import com.deliverygig.moonjyoung.repository.image.PickUpAreaImageRepository;
import com.deliverygig.moonjyoung.repository.image.StoreImageRepository;
import com.deliverygig.moonjyoung.repository.store.StoreDetailInfoRepository;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;
import com.deliverygig.moonjyoung.vo.delivery.ClosePickupTimeVO;
import com.deliverygig.moonjyoung.vo.delivery.LocationListVO;
import com.deliverygig.moonjyoung.vo.delivery.PickUpAreaVO;
import com.deliverygig.moonjyoung.vo.delivery.ShowPuaVO;
import com.deliverygig.moonjyoung.vo.delivery.StoreUnivTimeVO;
import com.deliverygig.moonjyoung.vo.delivery.ShowUnivListVO;
import com.deliverygig.moonjyoung.vo.delivery.ShowUnivTimeVO;
import com.deliverygig.moonjyoung.vo.delivery.UnivTimeVO;
import com.deliverygig.moonjyoung.vo.food.ShowFoodDetailOptionVO;
import com.deliverygig.moonjyoung.vo.food.ShowFoodOptionVO;
import com.deliverygig.moonjyoung.vo.food.ShowMenuDetailVO;
import com.deliverygig.moonjyoung.vo.store.ShowStoreInfoVO;
import com.deliverygig.moonjyoung.vo.store.ShowStoreListVO;
import com.deliverygig.moonjyoung.vo.store.StoreClosedDayInfoVO;
import com.deliverygig.moonjyoung.vo.store.StoreDetailInfoVO;
import com.deliverygig.moonjyoung.vo.store.StoreListInfoVO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
    @Autowired PickUpAreaImageRepository pickUpAreaImageRepository;
    @Autowired StoreImageRepository storeImageRepository;
    @Autowired FoodMenuInfoRepository foodMenuInfoRepository;
    @Autowired FoodOptionConnectRepository foodOptionConnectRepository;
    @Autowired FoodMenuOptionRepository foodMenuOptionRepository;
    @Autowired FoodDetailOptionRepository foodDetailOptionRepository;
    //@Autowired StoreImageRepository

    public Map<String, Object> getLocationList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<LocationListVO> returnList = new ArrayList<LocationListVO>();
        for (PickUpAreaEntity puaData : pickUpAreaRepository.findAll()) {
            LocationListVO vo = new LocationListVO();
            for (UnivTimeInfoEntity data : univTimeInfoRepository.findAll()) {
                if (data.getUnivInfoEntity().getUiSeq() == puaData.getUnivInfoEntity().getUiSeq()) {
                    vo.setUiSeq(data.getUnivInfoEntity().getUiSeq());
                    vo.setUiName(data.getUnivInfoEntity().getUiName());
                    vo.setPuaSeq(puaData.getPuaSeq());
                    vo.setPuaName(puaData.getPuaName());
                } else {
                    continue;
                }
                List<UnivTimeVO> timeVOList = getUnivTimeVOList(data.getUnivInfoEntity().getUiSeq());
                vo.setUnivTimeVOList(timeVOList);
            }
            returnList.add(vo);
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("message", "조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }

    public List<UnivTimeVO> getUnivTimeVOList(Long uiSeq) {
        List<UnivTimeVO> list = new ArrayList<UnivTimeVO>();
        for (UnivTimeInfoEntity data : univTimeInfoRepository.findAll()) {
            UnivTimeVO timeVO = new UnivTimeVO();
            if (uiSeq == data.getUnivInfoEntity().getUiSeq()) {
                timeVO.setUiSeq(data.getUnivInfoEntity().getUiSeq());
                timeVO.setTimeName(data.getUtiName());
                timeVO.setDeliTime1(data.getUtiPickupTime1());
                timeVO.setDeliTime2(data.getUtiPickupTime2());
            } else {
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
        resultMap.put("message", "배달지역 내 가게 주문시간 조회 완료");
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
        resultMap.put("message", "조회 완료");
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
        resultMap.put("message", "조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }

    // public Map<String, Object> getDeliveryStoreDetail() {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     List<StoreDetailInfoVO> returnList = new ArrayList<StoreDetailInfoVO>();
    //     for (StoreDetailInfoEntity data : storeDetailInfoRepository.findAll()) {
    //         StoreDetailInfoVO vo = new StoreDetailInfoVO();
    //         vo.setSdiOwnerWord(data.getSdiOwnerWord());
    //         vo.setSdiPhoneNumber(data.getSdiPhoneNumber());
    //         vo.setSdiAddress(data.getSdiAddress());
    //         vo.setSdiOwnerName(data.getSdiOwnerName());
    //         vo.setSdiStoreName(data.getSdiStoreName());
    //         vo.setSdiBusinessNumber(data.getSdiBusinessNumber());
    //         vo.setSdiOrigin(data.getSdiOrigin());
    //         List<ClosePickupTimeVO> list = new ArrayList<ClosePickupTimeVO>();
    //         for (StoreTimeDetailEntity data2 : storeTimeDetailRepository.findAll()) {
    //             if (data2.getStoreInfoEntity().getSiSeq()==data.getStoreInfoEntity().getSiSeq()) {
    //                 ClosePickupTimeVO vo2 = new ClosePickupTimeVO();
    //                 vo2.setName(data2.getUnivTimeInfoEntity().getUtiName());
    //                 vo2.setCloseTime(data2.getStdCloseTime());
    //                 vo2.setPickupTime(data2.getUnivTimeInfoEntity().getUtiPickupTime1());
    //                 list.add(vo2);
    //             }
    //         }
    //         vo.setClosePickupTimeVoList(list);
    //         vo.setSdiMinOrderPrice(data.getSdiDeliveryPrice());
    //         vo.setSdiDeliveryPrice(data.getSdiDeliveryPrice());
    //         returnList.add(vo);
    //     }
    //     resultMap.put("status", true);
    //     resultMap.put("code", HttpStatus.OK);
    //     resultMap.put("message", "조회 완료");
    //     resultMap.put("list", returnList);
    //     return resultMap;
    // }

    // 이하 코드는 20230120 이후 코드.
    // 각 페이지별로 띄워줄 리스트 정보를 다를 예정

    // 대학리스트 조회에 사용
    public Map<String, Object> getUnivList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowUnivListVO> returnList = new ArrayList<ShowUnivListVO>();

        for (UnivInfoEntity data : univInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "uiName"))) {
            ShowUnivListVO vo = new ShowUnivListVO(data);
            returnList.add(vo);
        }

        
        resultMap.put("status", true);
        if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(등록된 대학이 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("list", returnList);
            return resultMap;
        } else {
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("list", returnList);
            return resultMap;
        }
    }

    //localhost:8888/list/univ/search?keyword=대학
    public Map<String, Object> searchUniv(String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (keyword==null || keyword.equals("")) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            resultMap.put("message", "검색어를 입력하세요.");
        }
        else if (univInfoRepository.findByUiNameEquals(keyword)==null) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            resultMap.put("message", "해당하는 대학이 없습니다.");
        }
        else {
            ShowUnivListVO data = new ShowUnivListVO(univInfoRepository.findByUiNameEquals(keyword));
            resultMap.put("status", true);
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("message", "성공");
            resultMap.put("data", data);
        }
        return resultMap;
    }

    // 수령장소 조회에 사용
    public Map<String, Object> getpuaList(Long uiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowPuaVO> returnList = new ArrayList<>();

        for (PickUpAreaEntity data : pickUpAreaRepository.findAllByPuaUiSeq(uiSeq)) {
            ShowPuaVO vo = new ShowPuaVO(data);
            vo.setPuaiPuaSeq(pickUpAreaImageRepository.findByPuaiPuaSeq(data.getPuaSeq()).getPuaiSeq());
            returnList.add(vo);
        }

        resultMap.put("status", true);
        if (univInfoRepository.countByUiSeq(uiSeq) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 대학입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            return resultMap;
        } else if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(등록된 장소가 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
        } else {
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
        }
        resultMap.put("univ", univInfoRepository.findByUiSeq(uiSeq).getUiName());
        resultMap.put("list", returnList);
        return resultMap;
    }

    // 배달시간 조회에 사용
    public Map<String, Object> getUnivTimeList(Long uiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowUnivTimeVO> returnList = new ArrayList<ShowUnivTimeVO>();
        // for(StoreImageEntity data : storeImageRepository.find()); {
        //     ShowStoreListVO vo = new ShowStoreListVO();
        //     vo.setStoreName();

        // } 민경이가 만들다가 만 거 

        for (UnivTimeInfoEntity data : univTimeInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "utiCloseTime"))) {
            if (data.getUnivInfoEntity().getUiSeq() == uiSeq) {
                ShowUnivTimeVO vo = new ShowUnivTimeVO(data);
                returnList.add(vo);
            }
        }

        resultMap.put("status", true);
        if (univInfoRepository.countByUiSeq(uiSeq) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 대학입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            return resultMap;
        } else if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(등록된 시간이 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
        } else {
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
        }
        resultMap.put("univ", univInfoRepository.findByUiSeq(uiSeq).getUiName());
        resultMap.put("list", returnList);
        return resultMap;
    }

    public Map<String, Object> getStoreList(Long utiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowStoreListVO> returnList = new ArrayList<ShowStoreListVO>();


        for (StoreTimeDetailEntity data : storeTimeDetailRepository.findAll()) {
            if (data.getUnivTimeInfoEntity().getUtiSeq() == utiSeq) {
                ShowStoreListVO vo = new ShowStoreListVO(data);
                // System.out.println(vo);
                returnList.add(vo);
            }
        }

        resultMap.put("status", true);
        if (univTimeInfoRepository.countByUtiSeq(utiSeq) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 시간정보 입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            return resultMap;
        } else if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(배달하는 가게가 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
        } else {
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
        }
        resultMap.put("timeName", univTimeInfoRepository.findByUtiSeq(utiSeq).getUtiName());
        resultMap.put("list", returnList);
        return resultMap;
    }


    // 할인하는 모든 가게 조회
    public Map<String, Object> getDCStoreList(Long utiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowStoreListVO> returnList = new ArrayList<ShowStoreListVO>();

        for (StoreTimeDetailEntity data : storeTimeDetailRepository.findAll()) {
            if (data.getUnivTimeInfoEntity().getUtiSeq() == utiSeq) {
                ShowStoreListVO vo = new ShowStoreListVO(data);
                if(vo.getdiscount() > 0) {
                    returnList.add(vo);
                }
            }
        } 
        resultMap.put("status", true);

        if (univTimeInfoRepository.countByUtiSeq(utiSeq) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 시간정보 입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            return resultMap;
        } 
        else if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(배달하는 가게가 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
        } 
        else {
            resultMap.put("message", "할인가게 조회 완료");
            resultMap.put("code", HttpStatus.OK);
        }
        resultMap.put("timeName", univTimeInfoRepository.findByUtiSeq(utiSeq).getUtiName());
        resultMap.put("list", returnList);
        return resultMap;
    }


    //  할인율에 따라 가게 조회 정렬하기
    public Map<String, Object> getOrderByStoreList(Long utiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowStoreListVO> returnList = new ArrayList<ShowStoreListVO>();

        for (StoreTimeDetailEntity data : storeTimeDetailRepository.findAllByStdUtiSeq(utiSeq)) {
            // List<StoreInfoEntity> list =  storeInfoRepository.findAllByOrderBySiDiscount();
            ShowStoreListVO vo = new ShowStoreListVO(data);
            returnList.add(vo);
            returnList = returnList.stream().sorted(Comparator.comparing(ShowStoreListVO::getdiscount).reversed()).collect(Collectors.toList());
        }

        resultMap.put("status", true);
        if (univTimeInfoRepository.countByUtiSeq(utiSeq) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 시간정보 입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            return resultMap;
        } else if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(배달하는 가게가 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
        } else {
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
        }
        resultMap.put("timeName", univTimeInfoRepository.findByUtiSeq(utiSeq).getUtiName());
        resultMap.put("list", returnList);
        return resultMap;
    }

    // 가게 상세정보 조회에 사용
    public Map<String, Object> getStoreInfo(Long siSeq, Long utiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (storeInfoRepository.findById(siSeq).isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 가게입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
        } else if (univTimeInfoRepository.findById(utiSeq).isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("message", "해당하는 배달시간이 없습니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
        } else if (storeTimeDetailRepository.findByStdSiSeqAndStdUtiSeq(siSeq, utiSeq) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "이 가게는 이 시간/장소에 배달하지 않습니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
        } else {
            ShowStoreInfoVO returnData = new ShowStoreInfoVO(storeDetailInfoRepository.findBySdiSeq(siSeq),
                    storeTimeDetailRepository.findByStdSiSeqAndStdUtiSeq(siSeq, utiSeq));
            List<ClosePickupTimeVO> timeList = new ArrayList<ClosePickupTimeVO>();
            for (StoreTimeDetailEntity data : storeTimeDetailRepository.findAllByStdSiSeq(siSeq)) {
                if (data.getUnivTimeInfoEntity().getUnivInfoEntity() == univTimeInfoRepository.findByUtiSeq(utiSeq)
                        .getUnivInfoEntity()) {
                    ClosePickupTimeVO vo = new ClosePickupTimeVO(data);
                    if (data.getUnivTimeInfoEntity().getUtiSeq() == utiSeq) {
                        vo.setThisTime(true);
                    }
                    timeList.add(vo);
                }
            }
            returnData.setClosePickUpTimeList(timeList);
            resultMap.put("status", true);
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("data", returnData);
        }
        return resultMap;
    }

    //localhost:8888/list/store/search?keyword=닭
    public Map<String, Object> searchStore(String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowStoreListVO> resultList = new ArrayList<ShowStoreListVO>();
        if (keyword==null || keyword.equals("")) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            resultMap.put("message", "검색어를 입력하세요.");
            return resultMap;
        }
        // 1. keyword -> storeInfoEntity검색 -> List<StoreInfoEntity> -> List<Long(siSeq)>
        for (StoreInfoEntity data : storeInfoRepository.findAllBySiNameContaining(keyword)) {
            //resultList.add( new StoreInfoEntity(data));
            
            //resultList.add(new (Long)StoreInfoEntity(data));
            // resultList.add(new ShowStoreListVO(data));
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("message", "성공");
        resultMap.put("list", resultList);
        return resultMap;
    }

    // 메뉴 옵션 조회
    public Map<String, Object> getMenuOptionList(Long fmiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowFoodOptionVO> menuOptionList = new ArrayList<ShowFoodOptionVO>();
        if (foodOptionConnectRepository.findAllByFocFmiSeq(fmiSeq).size() == 0) {
            Optional<FoodMenuInfoEntity> entity = foodMenuInfoRepository.findById(fmiSeq);
            if (entity.isEmpty()) {
                resultMap.put("status", false);
                resultMap.put("message", "등록되지않은 메뉴번호 입니다.");
                resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
                return resultMap;
            } else {
                ShowMenuDetailVO vo = new ShowMenuDetailVO(entity.get());
                resultMap.put("status", true);
                resultMap.put("message", "옵션이 없는 메뉴");
                resultMap.put("code", HttpStatus.OK);
                resultMap.put("data", vo);
                return resultMap;
            }
        }
        ShowMenuDetailVO showMenuDetailVO = new ShowMenuDetailVO(
                foodOptionConnectRepository.findAllByFocFmiSeq(fmiSeq).get(0).getFoodMenuInfoEntity());

        for (FoodOptionConnectEntity data : foodOptionConnectRepository.findAllByFocFmiSeq(fmiSeq)) {
            List<ShowFoodDetailOptionVO> detailOptionList = new ArrayList<ShowFoodDetailOptionVO>();
            ShowFoodOptionVO vo = new ShowFoodOptionVO(data);

            for (FoodDetailOptionEntity data2 : data.getFoodMenuOptionEntity().getFdoEntityList()) {
                detailOptionList.add(new ShowFoodDetailOptionVO(data2));
            }

            vo.setDetailOptionList(detailOptionList);
            menuOptionList.add(vo);
        }
        showMenuDetailVO.setOptionList(menuOptionList);

        resultMap.put("status", true);
        resultMap.put("message", "조회 완료");
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("data", showMenuDetailVO);
        return resultMap;
    }



    // public List<StoreClosedDayInfoVO> getClosedDayVOList(Long scdiSiSeq) {
    //     List<StoreClosedDayInfoVO> list = new ArrayList<StoreClosedDayInfoVO>();
    //     for (StoreClosedDayEntity data : storeClosedDayRepository.findAll()) {
    //         StoreClosedDayInfoVO closedDayVO = new StoreClosedDayInfoVO();
    //         if (scdiSiSeq == data.getStoreInfoEntity().getSiSeq()) {
    //             closedDayVO.setScdi_day(data.getScdiDay());
    //             closedDayVO.setScdi_day_no(data.getScdiDayNo());
    //         } else {
    //             continue;
    //         }
    //         list.add(closedDayVO);
    //     }
    //     return list;
    // }

    // public StoreInfoEntity closedDaystatus() {
    //     LocalDate now = LocalDate.now();
    //     DayOfWeek dayOfWeek = now.getDayOfWeek();
    //     int dayOfWeekNumber = dayOfWeek.getValue();
    //     // StoreInfoEntity entity = new StoreInfoEntity();
    //     List<StoreClosedDayEntity> list = new ArrayList<StoreClosedDayEntity>();
    //     for (StoreClosedDayEntity data : list) {
    //         if (data.getScdiDayNo() == dayOfWeekNumber) {
    //             StoreInfoEntity entity = storeInfoRepository.findBySiSeq(data.getStoreInfoEntity().getSiSeq());
              
    //         }
    //     }
    //     // return  entity.setSiStatus(3);

    // }

}

 

    

    



