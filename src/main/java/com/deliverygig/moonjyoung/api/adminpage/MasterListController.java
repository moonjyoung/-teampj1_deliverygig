package com.deliverygig.moonjyoung.api.adminpage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deliverygig.moonjyoung.entity.account.MasterEntity;
import com.deliverygig.moonjyoung.repository.account.MasterRepository;
import com.deliverygig.moonjyoung.vo.account.MasterLoginInfoVO;
import com.deliverygig.moonjyoung.vo.account.MasterLoginVO;
import com.deliverygig.moonjyoung.vo.account.MasterVO;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/master")
public class MasterListController {
    @Autowired MasterRepository mrRepo;

    @GetMapping("/join")
    public String getAdminJoin() {
        return "/admin/master/join"; 
    }
    @PostMapping("/join")
    public String postMemberJoin(String id, String pwd, String nickname, Model model) {
        String eng_pattern = "^[a-zA-Z0-9]*$";
        Pattern p = Pattern.compile(eng_pattern);
        if(!p.matcher(id).matches()) {
            model.addAttribute("status", "invalidId");
            return "/admin/master/join";
        }
        String pwd_pattern = "^[a-zA-Z0-9`~!@#$%^&*()-_=+]{6,20}$";
        p = Pattern.compile(pwd_pattern);
        if(!p.matcher(pwd).matches()) {
            model.addAttribute("status", "invalidPwd");
            return "/admin/master/join";
        }
        String replacePwd = pwd.replaceAll(" ", "");
        if(pwd.length() != replacePwd.length()) {
            model.addAttribute("status", "whitespacePwd");
            model.addAttribute("id", id);
            return "/admin/master/join";
        }
        if(id.equals("") || id == null) {
            model.addAttribute("status", "emptyId");
            model.addAttribute("id", id);
            model.addAttribute("nickname", nickname);
            return "/admin/master/join";
        }
        if(pwd.equals("") || pwd == null) {
            model.addAttribute("status", "emptyPwd");
            model.addAttribute("id", id);
            model.addAttribute("nickname", nickname);
            return "/admin/master/join";
        }
        if(nickname.equals("") || nickname == null) {
            model.addAttribute("status", "emptyNickname");
            model.addAttribute("id", id);
            model.addAttribute("nickname", nickname);
            return "/admin/master/join";
        }
        if(mrRepo.countByMiId(id) > 0) {
            model.addAttribute("id", id);
            model.addAttribute("nickname", nickname);
            model.addAttribute("status", "duplicated");
            return "/admin/master/join";
        }
        else {
            mrRepo.save(MasterEntity.builder().miId(id).miPwd(pwd).miNickName(nickname).build());
        }
        model.addAttribute("status", true);
        return "/admin/master/login";
    }
  
    @GetMapping("/login") 
    public String getLogin(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        return "/admin/main";
    }
    @PostMapping("/login")
    public String postLogin(MasterLoginInfoVO login, Model model, HttpSession session) {
        MasterEntity loginUser = mrRepo.findByMiIdAndMiPwd(login.getMaster_id(), login.getMaster_pwd());
        if(loginUser == null) {
            model.addAttribute("loginStatus", "failed");
            model.addAttribute("message", "아이디 또는 비밀번호 오류입니다.");
            return "/admin/index";
        }
        session.setAttribute("loginUser", new MasterLoginVO(loginUser));
        return "redirect:/master/main";
    }
  
    @GetMapping("/list")
    public String getMemberList(Model model, Pageable pageable, HttpSession session) {
        Page<MasterEntity> page = mrRepo.findAll(pageable);
        List<MasterVO> accountList = new ArrayList<MasterVO>();
        for(MasterEntity a : page.getContent()) {
            if(a.getMiGrade() == 2) {
                accountList.add(new MasterVO(a));
            }
        }
        model.addAttribute("accountList", accountList); 
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalCount", page.getTotalElements());
        model.addAttribute("currentPage", page.getNumber());
        return "/admin/master/list";
    }
    
    @GetMapping("/status")
    public String getMemberStatusUpdate(@RequestParam Long seq, @RequestParam Integer status) {
        MasterEntity entity = mrRepo.findByMiSeq(seq);
        entity.setMiStatus(status);
        mrRepo.save(entity);
        return "redirect:/admin/master/list";
    }
    @GetMapping("/delete")
    public String memberDelete(@RequestParam Long seq) {
        MasterEntity entity = mrRepo.findByMiSeq(seq);
        mrRepo.delete(entity);
        return "redirect:/admin/master/list";
    }
    @GetMapping("/logout")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/master";
    }
}
