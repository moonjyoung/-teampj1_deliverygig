package com.deliverygig.moonjyoung.api.adminpage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deliverygig.moonjyoung.entity.account.CustomerInfoEntity;
import com.deliverygig.moonjyoung.repository.account.CustomerRepository;
import com.deliverygig.moonjyoung.repository.account.MasterRepository;
import com.deliverygig.moonjyoung.vo.account.CustomerListVO;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CunstomerListController {
    @Autowired CustomerRepository ctRepo;
    @Autowired MasterRepository mrRepo;
  
    @GetMapping("/list")
    public String getMemberList(Model model, Pageable pageable, HttpSession session, @RequestParam @Nullable String keyword) {
        Page<CustomerInfoEntity> page = ctRepo.findAll(pageable);
        List<CustomerListVO> customerList = new ArrayList<CustomerListVO>();
        for(CustomerInfoEntity c : page.getContent()) {
        if(c.getCiId().equals(keyword)) {
            customerList.add(new CustomerListVO(c));
            model.addAttribute("customerList", customerList);
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("totalCount", page.getTotalElements());
            model.addAttribute("currentPage", page.getNumber());
            return "/admin/customer/list";
            }
        customerList.add(new CustomerListVO(c));
        }
        model.addAttribute("customerList", customerList);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalCount", page.getTotalElements());
        model.addAttribute("currentPage", page.getNumber());
        return "/admin/customer/list";
    }
  
    @GetMapping("/delete")
    public String CustomerDelete(@RequestParam Long seq) {
        CustomerInfoEntity entity = ctRepo.findByCiSeq(seq);
        ctRepo.delete(entity);
        return "redirect:/customer/list";
    }
  
    @GetMapping("/logout")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/master/login";
    }
  
    @GetMapping("/status")
    public String getCustomerStatusUpdate(@RequestParam Long seq, @RequestParam Integer status) {
        CustomerInfoEntity entity = ctRepo.findByCiSeq(seq);
        entity.setCiStatus(status);
        ctRepo.save(entity);
        return "redirect:/customer/list";
    }
}
