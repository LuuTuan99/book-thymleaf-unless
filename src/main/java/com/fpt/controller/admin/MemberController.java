package com.fpt.controller.admin;

import com.fpt.entity.Member;
import com.fpt.service.admin.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/members")
public class MemberController {
    @Autowired
    MemberServiceImpl memberService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            Model model) {
        Page<Member> memberPage = memberService.findAll(PageRequest.of(page - 1, limit));
        model.addAttribute("members", memberPage.getContent());
        model.addAttribute("currentPage", memberPage.getPageable().getPageNumber() + 1);
        model.addAttribute("limit", memberPage.getPageable().getPageSize());
        model.addAttribute("totalPage", memberPage.getTotalPages());
        return "admin/member/list";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable long id, Model model) {
        Member member = memberService.getById(id);
        if (member == null) {
            return "error/404";
        }
        model.addAttribute("member", member);
        return "admin/member/detail";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login() {
        return "client/login-register/page-login";
    }

//    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
//    public String logoutSuccessfulPage(Model model) {
//        model.addAttribute("title", "Logout");
//        return "logoutSuccessfulPage";
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/403")
    public String accessDenied() {
        return "error/403";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String create(Model model) {
        model.addAttribute("member", new Member());
        return "client/login-register/page-register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String stores(@Valid Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "client/login-register/page-register";
        }
        memberService.register(member);
        return "success";
    }




}
