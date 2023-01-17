package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverygig.moonjyoung.service.ShowFoodService;

@RestController
public class MenuController {
    @Autowired ShowFoodService showFoodService;

    @GetMapping("/menu/list")
    public ResponseEntity<Object> getMenuList() {
        Map<String, Object> resultMap = showFoodService.getMenuList();
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
}
