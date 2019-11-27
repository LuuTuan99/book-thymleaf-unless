package com.fpt.controller.admin;

import com.fpt.config.ProjectConfig;
import com.fpt.entity.Book;
import com.fpt.entity.Category;
import com.fpt.entity.Member;
import com.fpt.entity.Publisher;
import com.fpt.service.admin.CategoryServiceImpl;
import com.fpt.specification.BookSpecification;
import com.fpt.specification.CategorySpecification;
import com.fpt.specification.SearchCriteria;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping(value = ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_CATEGORIES)
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            Model model) {
        Specification specification = Specification.where(null);

        if (keyword != null && keyword.length() > 0) {
            specification = specification
                    .and(new CategorySpecification(new SearchCriteria("keyword", "join", keyword)));
            model.addAttribute("keyword", keyword);
        }

        Page<Category> categoryPage = categoryService.findAllActive(specification, PageRequest.of(page - 1, limit));
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", categoryPage.getPageable().getPageNumber() + 1);
        model.addAttribute("limit", categoryPage.getPageable().getPageSize());
        model.addAttribute("totalPage", categoryPage.getTotalPages());
        return "admin/category/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable long id, Model model) {
        Category category = categoryService.getById(id);
        if (category == null) {
            return "error/404";
        }
        Set<Book> books = category.getBooks();
        model.addAttribute("category", category);
        model.addAttribute("books", books);
        return "admin/category/detail";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/create";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String store(@Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/category/create";
        }
        categoryService.save(category);
        return "redirect:"+ ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_CATEGORIES;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (StringUtils.isEmpty(keyword)) {
            return "redirect:"+ ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_CATEGORIES;
        }

        model.addAttribute("categories", categoryService.search((keyword)));
        return "admin/category/list";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable(value = "id", required = false) long id, HttpServletResponse response) {
        Category category = categoryService.getById(id);
        if (category == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new Gson().toJson("Error");
        }
        categoryService.delete(id);
        response.setStatus(HttpStatus.OK.value());
        return new Gson().toJson("Ok");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update/{id}")
    public String updateCategory(@PathVariable long id, Model model) {
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
    public String update(@PathVariable long id, Category category) {
        categoryService.update(id, category);
        return "redirect:"+ ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_CATEGORIES;
    }


}
