package com.fpt.controller.admin;

import com.fpt.entity.Book;
import com.fpt.entity.Category;
import com.fpt.service.admin.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/category/list";
    }

    @GetMapping(value = "/by_books/{id}")
    public String detail(@PathVariable long id, Model model) {
        Category category = categoryService.getById(id);
        Set<Book> books = category.getBooks();
        model.addAttribute("category", category);
        model.addAttribute("books", books);
        return "admin/category/detail";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/create";
    }

    @PostMapping(value = "/create")
    public String store(@Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/category/create";
        }
        categoryService.save(category);
        return "redirect:/categories/list";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam(value = "name", required = false) String name, Model model) {
        if (StringUtils.isEmpty(name)) {
            return "redirect:/categories/list";
        }

        model.addAttribute("categories", categoryService.search((name)));
        return "admin/category/list";
    }

    @GetMapping(value = "/update/{id}")
    public String updateAuthor(@PathVariable long id, Model model) {
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable long id, Category category) {
        categoryService.update(id, category);
        return "redirect:/categories/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = false) long id, RedirectAttributes redirectAttributes) {
        categoryService.delete(id);
        redirectAttributes.addFlashAttribute("Success!", "Deleted contact successfully!");
        return "redirect:/categories/list";
    }
}
