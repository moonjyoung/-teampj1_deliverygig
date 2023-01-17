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

import com.deliverygig.moonjyoung.entity.account.OwnerEntity;
import com.deliverygig.moonjyoung.repository.account.OwnerRepository;
import com.deliverygig.moonjyoung.vo.account.OwnerListVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/owner")
public class OwnerListController {
    @Autowired OwnerRepository owRepo;

    @GetMapping("/list")
    public String getMemberList(Model model, Pageable pageable, HttpSession session) {
        Page<OwnerEntity> page = owRepo.findAll(pageable);
        List<OwnerListVO> ownerList = new ArrayList<OwnerListVO>();
        for(OwnerEntity o : page.getContent()) {
            ownerList.add(new OwnerListVO(o));
        }
        model.addAttribute("ownerList", ownerList);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalCount", page.getTotalElements());
        model.addAttribute("currentPage", page.getNumber());
        return "/admin/owner/list";
    }
  
    @GetMapping("/delete")
    public String CustomerDelete(@RequestParam Long seq) {
        OwnerEntity entity = owRepo.findByOiSeq(seq);
        owRepo.delete(entity);
        return "redirect:/owner/list";
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/master/login";
    }

    @GetMapping("/status")
    public String getCustomerStatusUpdate(@RequestParam Long seq, @RequestParam Integer status) {
        OwnerEntity entity = owRepo.findByOiSeq(seq);
        entity.setOiStatus(status);
        owRepo.save(entity);
        return "redirect:/owner/list";
    }
}
