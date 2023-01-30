package com.deliverygig.moonjyoung.api.storepage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deliverygig.moonjyoung.vo.store.AddstoreInfoVo;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/store")
public class StoreCController {
    @Autowired
    StoreService sService;
    @PostMapping("/add")
    public String postAddStore(AddstoreInfoVo data, Model model, HttpSession session) {
        Map<String, Object> map = sService.addStoreInfo(data, session);
          if((boolean)map.get("status")){
            return "redirect:/";
        }
        model.addAttribute("inputdata", data);
        model.addAttribute("message", map.get("message"));
        return "/store/add";
        }
    @GetMapping("/add")
    public String getAddStore(){
        return "/store/add";
    }
    @GetMapping("/main")
    public String getMain() {
        return "/store/main";
    }

    }   

