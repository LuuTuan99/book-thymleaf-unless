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
public class AuthorController {
    @Autowired
    AuthorServiceImpl authorService;

    @GetMapping(value = "/index")
    public String list(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/showList";
    }

    @GetMapping(value = "/{id}")
    public String getDetail(@PathVariable long id, Model model) {
        model.addAttribute("author", authorService.getById(id));
        return "authors/detail";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("author", new Author());
        return "authors/form";
    }

    @PostMapping(value = "/create")
    public String store(@Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/authors/form";
        }
        authorService.save(author);
        return "redirect:/index";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam(value = "term", required = false) String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/index";
        }

        model.addAttribute("authors", authorService.search((term)));
        return "admin/authors/list";
    }

    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable(value = "id", required = false) long id, RedirectAttributes redirectAttributes) {
        authorService.delete(id);
        redirectAttributes.addFlashAttribute("Success!", "Deleted contact successfully!");
        return "redirect:/index";
    }

    @GetMapping(value = "/update/{id}")
    public String updateAuthor(@PathVariable long id, Model model) {
        Author author = authorService.getById(id);
        model.addAttribute("author", author);
        return "authors/edit";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable long id, Author author) {
        authorService.update(id, author);
        return "redirect:/index";
    }
}
