package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deliverygig.moonjyoung.service.FoodBasketService;

@Controller
public class FoodBasketController {
    @Autowired FoodBasketService foodBasketService;

    @GetMapping("/mycart")
    public ResponseEntity<Object> getMycart(/* @RequestParam Long ciSeq */) {
        Map<String, Object> resultMap = foodBasketService.getFoodBasket();
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
}
