package com.deliverygig.moonjyoung.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.review.ReviewEntity;
import com.deliverygig.moonjyoung.repository.mycart.BasketMenuOptionsCombineRepository;
import com.deliverygig.moonjyoung.repository.review.ReviewRepository;
import com.deliverygig.moonjyoung.vo.review.AddReviewVO;
import com.deliverygig.moonjyoung.vo.review.StoreReviewListVO;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository rRepo;
    @Autowired
    BasketMenuOptionsCombineRepository combineRepo;

    // 리뷰 등록
    public Map<String, Object> addReview(AddReviewVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (combineRepo.countByBmocSeq(data.getOrder_seq()) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "잘못된 주문번호입니다. ");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        if (rRepo.countByRiBmocSeq(data.getOrder_seq()) == 1) {
            resultMap.put("status", false);
            resultMap.put("message", "해당 주문번호의 리뷰가 이미 존재합니다. ");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        ReviewEntity entity = new ReviewEntity();
        entity.setRiContents(data.getRi_contents());
        entity.setRiScore(data.getRi_score());
        entity.setBasketMenuOptionsCombineEntity(combineRepo.findById(data.getOrder_seq()).get());
        rRepo.save(entity);
        resultMap.put("status", true);
        resultMap.put("message", "리뷰가 등록되었습니다.");
        resultMap.put("code", HttpStatus.CREATED);
        return resultMap;
    }

    // 가게 리뷰 조회 
    public Map<String, Object> getStoreReview(Long siSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<StoreReviewListVO> returnList = new ArrayList<StoreReviewListVO>();
        for (ReviewEntity data : rRepo.findAll()) {
            StoreReviewListVO vo = new StoreReviewListVO();
            if (data.getBasketMenuOptionsCombineEntity().getFoodMenuInfoEntity().getFoodCategoryEntity()
                    .getStoreInfoEntity().getSiSeq() == siSeq) {
                if (data.getRiStatus() == 1) {
                    vo.setCiSeq(data.getBasketMenuOptionsCombineEntity().getBasketInfoEntity().getBiCiSeq());
                    vo.setMenu(data.getBasketMenuOptionsCombineEntity().getFoodMenuInfoEntity().getFmiName());
                    vo.setMenuOption(data.getBasketMenuOptionsCombineEntity().getBmocOptionAll());
                    vo.setReviewScore(data.getRiScore());
                    vo.setReviewContent(data.getRiContents());
                    vo.setReviewDt(data.getRiDt());
                }
            } else {
                continue;
            }
            returnList.add(vo);
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("message", "조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }

    // 내 리뷰 조회 
    public Map<String, Object> getMyReview(Long ciSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<StoreReviewListVO> returnList = new ArrayList<StoreReviewListVO>();
        for (ReviewEntity data : rRepo.findAll()) {
            StoreReviewListVO vo = new StoreReviewListVO();
            if (data.getBasketMenuOptionsCombineEntity().getBasketInfoEntity().getBiCiSeq() == ciSeq) {
                vo.setCiSeq(data.getBasketMenuOptionsCombineEntity().getBasketInfoEntity().getBiCiSeq());
                vo.setMenu(data.getBasketMenuOptionsCombineEntity().getFoodMenuInfoEntity().getFmiName());
                vo.setMenuOption(data.getBasketMenuOptionsCombineEntity().getBmocOptionAll());
                vo.setReviewScore(data.getRiScore());
                vo.setReviewContent(data.getRiContents());
                vo.setReviewDt(data.getRiDt());
            } else {
                continue;
            }
            returnList.add(vo);
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("message", "조회 완료");
        resultMap.put("list", returnList);
        return resultMap;
    }
}
