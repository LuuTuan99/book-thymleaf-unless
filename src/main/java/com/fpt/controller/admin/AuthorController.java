package com.fpt.controller.admin;

import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.service.admin.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping(value = "/authors")
public class AuthorController {
    @Autowired
    AuthorServiceImpl authorService;

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "admin/author/list";
    }


    @GetMapping(value = "/by_books/{id}")
    public String detail(@PathVariable long id, Model model) {
        Author author = authorService.getById(id);
        Set<Book> books = author.getBooks();
        model.addAttribute("author", author);
        model.addAttribute("books", books);
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
    public String search(@RequestParam(value = "name", required = false) String name, Model model) {
        if (StringUtils.isEmpty(name)) {
            return "redirect:/authors/list";
        }

        model.addAttribute("authors", authorService.search((name)));
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
