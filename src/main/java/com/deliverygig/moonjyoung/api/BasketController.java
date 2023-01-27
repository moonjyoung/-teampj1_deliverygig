package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverygig.moonjyoung.service.BasketService;
import com.deliverygig.moonjyoung.vo.mycart.AddBasketMenuOptionVO;

@RestController
public class BasketController {
    @Autowired BasketService basketService;

    @GetMapping("/basket")
    public ResponseEntity<Object> getBasket(@RequestParam Long ciSeq) {
        Map<String, Object> resultMap = basketService.getBasketInfo(ciSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    @PostMapping("/order/basket")
    public ResponseEntity<Object> postBasket(@RequestBody AddBasketMenuOptionVO data) {
        Map<String, Object> resultMap = basketService.getMenuOptions(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
}
