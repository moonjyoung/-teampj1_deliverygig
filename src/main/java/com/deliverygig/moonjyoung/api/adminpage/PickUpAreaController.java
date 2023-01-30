package com.deliverygig.moonjyoung.api.adminpage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pickup")
public class PickUpAreaController {
  @Autowired PickUpAreaService pService;

  @GetMapping("/list")
  public String getGenreList(Model model, @RequestParam @Nullable String keyword, @PageableDefault
  (size = 10, sort = "puaUiSeq", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
    if (keyword == null) keyword = "";
    model.addAttribute("result", pService.getPickUpList(keyword, pageable));
    model.addAttribute("keyword", keyword);
    return "/admin/university/universityDetail";
  }

  @GetMapping("/add")
  public String LocationAdd() {
    return "/admin/university/pickupPlaceAdd";
  }
  @PostMapping("/add")
  public String LocationAdd(String univ, String pickupArea, Model model) {
      Map<String, Object> resultMap = pService.addPickUpArea(univ, pickupArea);
      if((Boolean)resultMap.get("status")) {
        return "redirect:/pickup/list";
      }
      else {
        model.addAttribute("univ", univ);
        model.addAttribute("result", resultMap);
        return "/admin/university/pickupPlaceAdd";
      }
  }
}
