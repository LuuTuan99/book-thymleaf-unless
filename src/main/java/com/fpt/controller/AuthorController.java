package com.fpt.controller;

import com.fpt.entity.Author;
import com.fpt.service.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/authors")
public class AuthorController {
    @Autowired
    AuthorServiceImpl authorService;
    //demo shop index
    @GetMapping(value = "/demo")
    public String demo(){
        return "client/shop-index";
    }
    //demo shop list item
    @GetMapping(value = "/demo1")
    public String demo1(){
        return "client/shop-product-list";
    }
    //demo shop item
    @GetMapping(value = "/demo2")
    public String demo2(){
        return "client/shop-item";
    }
    //demo page login
    @GetMapping(value = "/login")
    public String login(){
        return "client/login-register/page-login";
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "admin/author/list";
    }

    @GetMapping(value = "/{id}")
    public String getDetail(@PathVariable long id, Model model) {
        model.addAttribute("author", authorService.getById(id));
        return "admin/author/detail";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("author", new Author());
        return "admin/author/create";
    }

        @PostMapping(value = "/create")
        public String store(@Valid Author author, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "/admin/author/create";
            }
            authorService.save(author);
            return "redirect:/authors/list";
        }

    @GetMapping(value = "/search")
    public String search(@RequestParam(value = "term", required = false) String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/authors/list";
        }

        model.addAttribute("authors", authorService.search((term)));
        return "admin/author/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = false) long id, RedirectAttributes redirectAttributes) {
        authorService.delete(id);
        redirectAttributes.addFlashAttribute("Success!", "Deleted contact successfully!");
        return "redirect:/authors/list";
    }

    @GetMapping(value = "/update/{id}")
    public String updateAuthor(@PathVariable long id, Model model) {
        Author author = authorService.getById(id);
        model.addAttribute("author", author);
        return "admin/author/edit";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable long id, Author author) {
        authorService.update(id, author);
        return "redirect:/authors/list";
    }
}
