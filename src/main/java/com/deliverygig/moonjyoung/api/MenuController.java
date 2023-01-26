package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverygig.moonjyoung.service.ShowFoodService;
import com.deliverygig.moonjyoung.service.VOService;

@RestController
@RequestMapping("/store/menu")
public class MenuController {
    @Autowired VOService voService;
    @Autowired ShowFoodService showFoodService;

    // 가게 메뉴리스트 조회
    @GetMapping("/list")
    public ResponseEntity<Object> getMenuList(@RequestParam Long siSeq) {
        Map<String, Object> resultMap = showFoodService.getMenuList(siSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    // 메뉴 옵션리스트 조회
    @GetMapping("/selectOption")
    public ResponseEntity<Object> getMenuOptionList(@RequestParam Long menuSeq) {
        Map<String, Object> resultMap = voService.getMenuOptionList(menuSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
}
