package com.fpt.controller.admin;

import com.fpt.entity.Author;
import com.fpt.entity.Member;
import com.fpt.entity.Publisher;
import com.fpt.service.admin.MemberServiceImpl;
import com.fpt.specification.AuthorSpecification;
import com.fpt.specification.MemberSpecification;
import com.fpt.specification.SearchCriteria;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/members")
public class MemberController {
    @Autowired
    MemberServiceImpl memberService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            Model model) {

        Specification specification = Specification.where(null);
        if (keyword != null && keyword.length() > 0) {
            specification = specification
                    .and(new MemberSpecification(new SearchCriteria("username", "=", keyword)))
                    .or(new MemberSpecification(new SearchCriteria("role", "=", keyword)));
        }
        Page<Member> memberPage = memberService.findAllActive(specification, PageRequest.of(page - 1, limit));

        model.addAttribute("keyword", keyword);

        model.addAttribute("members", memberPage.getContent());
        model.addAttribute("currentPage", memberPage.getPageable().getPageNumber() + 1);
        model.addAttribute("limit", memberPage.getPageable().getPageSize());
        model.addAttribute("totalPage", memberPage.getTotalPages());
        return "admin/member/list";

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable(value = "id", required = false) long id, HttpServletResponse response) {
        Member member = memberService.getById(id);
        if (member == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new Gson().toJson("Error");
        }
        memberService.delete(id);
        response.setStatus(HttpStatus.OK.value());
        return new Gson().toJson("Ok");
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
    public String stores(@Valid Member member, BindingResult bindingResult, HttpServletRequest request,Model model) {

        String usernameInput = request.getParameter("username");
        Member existMember = memberService.getByUsername(usernameInput);
        model.addAttribute("existMember",existMember);
        if (bindingResult.hasErrors() || existMember!=null ) {
            return "client/login-register/page-register";
        }
        memberService.register(member);
        return "success";
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




}
