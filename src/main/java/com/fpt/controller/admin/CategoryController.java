package com.fpt.controller.admin;

import com.fpt.config.ProjectConfig;
import com.fpt.entity.Book;
import com.fpt.entity.Category;
import com.fpt.service.admin.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping(value = ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_CATEGORIES)
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            Model model) {
        Page<Category> categoryPage = categoryService.findAll(PageRequest.of(page - 1, limit));
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

    @RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = false) long id, RedirectAttributes redirectAttributes) {
        categoryService.delete(id);
        redirectAttributes.addFlashAttribute("Success!", "Deleted contact successfully!");
        return "redirect:"+ ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_CATEGORIES;
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
