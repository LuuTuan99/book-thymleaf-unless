package com.fpt.controller.admin;

import com.fpt.entity.Member;
import com.fpt.service.admin.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/members")
public class MemberController {
    @Autowired
    MemberServiceImpl memberService;

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("members", memberService.getList(1, 3));
        return "admin/member/list";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "client/login-register/page-login";
    }

    @GetMapping(value = "/register")
    public String create(Model model) {
        model.addAttribute("member", new Member());
        return "client/login-register/page-register";
    }

    @PostMapping(value = "/register")
    public String stores(@Valid Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "client/login-register/page-register";
        }
        memberService.register(member);
        return "success";
    }
}
