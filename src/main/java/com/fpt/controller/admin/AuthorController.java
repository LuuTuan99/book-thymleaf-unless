package com.fpt.controller.admin;

import com.fpt.config.ProjectConfig;
import com.fpt.entity.Author;
import com.fpt.service.admin.AuthorServiceImpl;
import com.fpt.specification.AuthorSpecification;
import com.fpt.specification.SearchCriteria;
import com.google.gson.Gson;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(value = ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_AUTHORS)
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            Model model) {
        Specification specification = Specification.where(null);
        if (keyword != null && keyword.length() > 0) {
            specification = specification
                    .and(new AuthorSpecification(new SearchCriteria("name", "=", keyword)))
                    .or(new AuthorSpecification(new SearchCriteria("description", "=", keyword)));
        }
        Page<Author> authorPage = authorService.findAllActive(specification, PageRequest.of(page - 1, limit));
        model.addAttribute("keyword", keyword);
        model.addAttribute("authors", authorPage.getContent());
        model.addAttribute("currentPage", authorPage.getPageable().getPageNumber() + 1);
        model.addAttribute("limit", authorPage.getPageable().getPageSize());
        model.addAttribute("totalPage", authorPage.getTotalPages());
        return "admin/author/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable long id, Model model) {
        Author author = authorService.getById(id);
        if (author == null) {
            return "error/404";
        }
        model.addAttribute("author", author);
        return "admin/author/detail";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(Model model) {
        model.addAttribute("author", new Author());
        return "admin/author/create";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String store(@Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/author/create";
        }
        authorService.save(author);
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_AUTHORS;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (StringUtils.isEmpty(keyword)) {
            return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_AUTHORS;
        }
        model.addAttribute("authors", authorService.search((keyword)));
        return "admin/author/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = false) long id, RedirectAttributes redirectAttributes) {
        Author author = authorService.getById(id);
        if (author == null) {
            return "error/404";
        }
        authorService.delete(id);
        redirectAttributes.addFlashAttribute("Success!", "Deleted contact successfully!");
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_AUTHORS;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/ajax-delete/{id}")
    @ResponseBody
    public String deleteWithAjax(@PathVariable(value = "id", required = false) long id, HttpServletResponse response) {
        Author author = authorService.getById(id);
        if (author == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new Gson().toJson("Error");
        }
        authorService.delete(id);
        response.setStatus(HttpStatus.OK.value());
        return new Gson().toJson("Ok");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update/{id}")
    public String updateAuthor(@PathVariable long id, Model model) {
        Author author = authorService.getById(id);
        model.addAttribute("author", author);
        return "admin/author/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
    public String update(@PathVariable long id, Author author) {
        authorService.update(id, author);
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_AUTHORS;
    }

    @GetMapping("/demo")
    public String demo(){
        return "admin/author/demo";
    }
}
