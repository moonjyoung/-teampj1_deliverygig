package com.deliverygig.moonjyoung.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.food.FoodMenuOptionEntity;
import com.deliverygig.moonjyoung.entity.mycart.BasketInfoEntity;
import com.deliverygig.moonjyoung.entity.mycart.BasketMenuOptionsCombineEntity;
import com.deliverygig.moonjyoung.repository.account.CustomerRepository;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.delivery.StoreTimeDetailRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivTimeInfoRepository;
import com.deliverygig.moonjyoung.repository.food.FoodDetailOptionRepository;
import com.deliverygig.moonjyoung.repository.food.FoodMenuInfoRepository;
import com.deliverygig.moonjyoung.repository.food.FoodMenuOptionRepository;
import com.deliverygig.moonjyoung.repository.food.FoodOptionConnectRepository;
import com.deliverygig.moonjyoung.repository.mycart.BasketInfoRepository;
import com.deliverygig.moonjyoung.repository.mycart.BasketMenuOptionsCombineRepository;
import com.deliverygig.moonjyoung.vo.food.ShowFoodDetailOptionVO;
import com.deliverygig.moonjyoung.vo.food.ShowMenuDetailVO;
import com.deliverygig.moonjyoung.vo.mycart.AddBasketMenuOptionVO;
import com.deliverygig.moonjyoung.vo.mycart.ShowBasketMenuOptionVO;
import com.deliverygig.moonjyoung.vo.mycart.ShowBasketMenuVO;
import com.deliverygig.moonjyoung.vo.mycart.ShowBasketVO;

@Service
public class BasketService {
    @Autowired PickUpAreaRepository pickupAreaRepository;
    @Autowired UnivTimeInfoRepository univTimeInfoRepository;
    @Autowired StoreTimeDetailRepository storeTimeDetailRepository;
    @Autowired FoodMenuInfoRepository foodMenuInfoRepository;
    @Autowired FoodOptionConnectRepository foodOptionConnectRepository;
    @Autowired FoodMenuOptionRepository foodMenuOptionRepository;
    @Autowired FoodDetailOptionRepository foodDetailOptionRepository;
    @Autowired CustomerRepository customerRepository;
    @Autowired BasketInfoRepository basketInfoRepository;
    @Autowired BasketMenuOptionsCombineRepository basketMenuOptionsCombineRepository;

    // 장바구니 조회
    public Map<String, Object> getBasketInfo(Long ciSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        if (basketInfoRepository.findByBiCiSeqAndBiStatus(ciSeq, 1)==null) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 장바구니입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            ShowBasketVO vo = new ShowBasketVO(basketInfoRepository.findByBiCiSeqAndBiStatus(ciSeq, 1));
            Integer totalPrice = 0;
            List<ShowBasketMenuVO> menuList = new ArrayList<ShowBasketMenuVO>();
            for (BasketMenuOptionsCombineEntity data : basketInfoRepository.findByBiCiSeqAndBiStatus(ciSeq, 1).getBmocEntityList()) {
                menuList.add(new ShowBasketMenuVO(data));
                totalPrice += data.getBmocPrice()*data.getBmocCount();
            }
            vo.setMenuList(menuList);
            vo.setCiName(customerRepository.findByCiSeq(ciSeq).getCiName());
            vo.setTotalPrice(totalPrice);

            BasketInfoEntity entity = basketInfoRepository.findByBiCiSeqAndBiStatus(ciSeq, 1);
                entity.setBiCiSeq(ciSeq);
                entity.setBiPrice(totalPrice);
                
                basketInfoRepository.save(entity);

            resultMap.put("status", true);
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("data", vo);
        }

        return resultMap;
    }
    // 구매목록 조회
    public Map<String, Object> getOrderHistory(Long ciSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowBasketVO> orderHistory = new ArrayList<ShowBasketVO>();

        if (basketInfoRepository.findAllByBiCiSeq(ciSeq).size()==0) {
            resultMap.put("status", false);
            resultMap.put("message", "결제내역이 없습니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            return resultMap;
        }
        else {
            for (BasketInfoEntity data : basketInfoRepository.findAllByBiCiSeq(ciSeq)) {
                ShowBasketVO vo = new ShowBasketVO(data);
                List<ShowBasketMenuVO> list = new ArrayList<ShowBasketMenuVO>();
                for (BasketMenuOptionsCombineEntity entity : data.getBmocEntityList()) {
                    list.add(new ShowBasketMenuVO(entity));
                }
                vo.setMenuList(list);
                vo.setTotalPrice(data.getBiPrice());
                orderHistory.add(vo);
            }

            resultMap.put("status", true);
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("data", orderHistory);
            return resultMap;
        }
    }

    // 장바구니창에서 결제완료
    public Map<String, Object> postBasketInfo(Long ciSeq, Long puaSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (basketInfoRepository.findByBiCiSeqAndBiStatus(ciSeq, 1)==null) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 장바구니입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            ShowBasketVO vo = new ShowBasketVO();
            Integer totalPrice = 0;
            List<ShowBasketMenuVO> menuList = new ArrayList<ShowBasketMenuVO>();
            for (BasketMenuOptionsCombineEntity data : basketInfoRepository.findByBiCiSeqAndBiStatus(ciSeq, 1).getBmocEntityList()) {
                menuList.add(new ShowBasketMenuVO(data));
                totalPrice += data.getBmocPrice()*data.getBmocCount();
            }
            vo.setMenuList(menuList);
            if (menuList.size()==0) {
                resultMap.put("status", true);
                resultMap.put("message", "조회 완료(비어있는 장바구니)");
                resultMap.put("code", HttpStatus.OK);
                resultMap.put("data", vo);
            }
            else {
                // 랜덤 주문번호(10자리) 생성
                Random rnd =new Random();
                StringBuffer buf =new StringBuffer();
                // 앞의 2자리는 회원번호와 연동되게
                buf.append((char)(65+ciSeq%26));
                buf.append((int)(ciSeq%10));
                // 뒤의 8자리는 랜덤으로
                for (int i=0; i<8; i++) {
                    // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
                    if (rnd.nextBoolean()) {
                        buf.append((char)((int)(rnd.nextInt(26))+97));
                    }
                    else{
                        buf.append((rnd.nextInt(10)));
                    }
                }
                vo.setBiNumber(buf.toString());
                vo.setBiRegDt(LocalDateTime.now());
                vo.setCiName(customerRepository.findByCiSeq(ciSeq).getCiName());
                vo.setPuaName(pickupAreaRepository.findByPuaSeq(puaSeq).getPuaName());
                vo.setTotalPrice(totalPrice);

                BasketInfoEntity entity = basketInfoRepository.findByBiCiSeqAndBiStatus(ciSeq, 1);
                entity.setBiCiSeq(ciSeq);
                entity.setBiNumber(vo.getBiNumber());
                entity.setBiRegDt(vo.getBiRegDt());
                entity.setBiPuaName(vo.getPuaName());
                entity.setBiPrice(totalPrice);
                entity.setBiStatus(2);
                
                basketInfoRepository.save(entity);

                resultMap.put("status", true);
                resultMap.put("message", "조회 완료");
                resultMap.put("code", HttpStatus.OK);
                resultMap.put("data", vo);
            }
        }
        return resultMap;
    }

    // 메뉴 추가
    public Map<String, Object> getMenuOptions(AddBasketMenuOptionVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        ShowBasketMenuOptionVO vo = new ShowBasketMenuOptionVO();
        // Long fmiSeq = data.getBfVO().getSeq();
        Long fmiSeq = data.getFmiSeq();
        Integer price = (int)(foodMenuInfoRepository.findByFmiSeq(fmiSeq).getFmiPrice()*(1-(foodMenuInfoRepository.findByFmiSeq(fmiSeq).getFoodCategoryEntity().getStoreInfoEntity().getSiDiscount())/100.0));
        
        vo.setSiName(foodMenuInfoRepository.findByFmiSeq(data.getFmiSeq()).getFoodCategoryEntity().getStoreInfoEntity().getSiName());
        vo.setCloseTime(storeTimeDetailRepository.findById(data.getStdSeq()).get().getStdCloseTime());
        vo.setDeliveryTime(storeTimeDetailRepository.findById(data.getStdSeq()).get().getUnivTimeInfoEntity().getUtiPickupTime1());
        vo.setMenuName(foodMenuInfoRepository.findByFmiSeq(fmiSeq).getFmiName());

        List<ShowFoodDetailOptionVO> sfdoVOList = new ArrayList<ShowFoodDetailOptionVO>();
        for (int i=0; i<data.getFdoSeqList().size(); i++) {
            sfdoVOList.add(new ShowFoodDetailOptionVO(foodDetailOptionRepository.findById(data.getFdoSeqList().get(i)).get()));
        }
        vo.setOptionAll(getOptionName(fmiSeq, sfdoVOList));
        price += getOptionPrice(fmiSeq, sfdoVOList);
        vo.setPrice(price);
        vo.setCount(data.getCount());

        Long biSeq = 0L;
        if  (basketInfoRepository.findByBiCiSeqAndBiStatus(data.getCiSeq(), 1)==null) {
            BasketInfoEntity entity = new BasketInfoEntity(data.getCiSeq());
            basketInfoRepository.save(entity);
        }
        biSeq = basketInfoRepository.findByBiCiSeqAndBiStatus(data.getCiSeq(), 1).getBiSeq();
        
        BasketMenuOptionsCombineEntity entity = new BasketMenuOptionsCombineEntity(biSeq, data.getStdSeq(), data.getFmiSeq(), vo.getOptionAll(), vo.getPrice(), vo.getCount());

        basketMenuOptionsCombineRepository.save(entity);

        resultMap.put("status", true);
        resultMap.put("message", "추가 완료");
        resultMap.put("code", HttpStatus.ACCEPTED);
        resultMap.put("data", vo);
        return resultMap;
    }

    public String getOptionName(Long fmiSeq, List<ShowFoodDetailOptionVO> sfdoVOList) {
        String optionAll = "";
        if (foodOptionConnectRepository.findAllByFocFmiSeq(fmiSeq).size()!=0) {
            for (int i=0; i<sfdoVOList.size(); i++) {
                if (i==0) {
                    optionAll += foodDetailOptionRepository.findById(sfdoVOList.get(i).getOptionSeq()).get().getFoodMenuOptionEntity().getFmoName()+"[";
                    optionAll += sfdoVOList.get(i).getOptionName();
                }
                else if (foodDetailOptionRepository.findById(sfdoVOList.get(i).getOptionSeq()).get().getFoodMenuOptionEntity().getFmoSeq()==foodDetailOptionRepository.findById(sfdoVOList.get(i-1).getOptionSeq()).get().getFoodMenuOptionEntity().getFmoSeq()) {
                    optionAll += ","+sfdoVOList.get(i).getOptionName();
                    if (i==sfdoVOList.size()-1) {
                        optionAll += "]";
                    }
                }
                else if (foodDetailOptionRepository.findById(sfdoVOList.get(i).getOptionSeq()).get()!=foodDetailOptionRepository.findById(sfdoVOList.get(i-1).getOptionSeq()).get()) {
                    optionAll += "],"+foodDetailOptionRepository.findById(sfdoVOList.get(i).getOptionSeq()).get().getFoodMenuOptionEntity().getFmoName()+"[";
                    if (i==sfdoVOList.size()-1) {
                        optionAll += sfdoVOList.get(i).getOptionName()+"]";
                    }
                    else {
                        optionAll += sfdoVOList.get(i).getOptionName();
                    }
                }
            }
        }
        return optionAll;
    }

    public Integer getOptionPrice(Long fmiSeq, List<ShowFoodDetailOptionVO> sfdoVOList) {
        Integer price = 0;
        if (foodOptionConnectRepository.findAllByFocFmiSeq(fmiSeq).size()!=0) {
            for (ShowFoodDetailOptionVO data : sfdoVOList) {
                price += data.getOptionPrice();
            }
        }
        return price;
    }
}
