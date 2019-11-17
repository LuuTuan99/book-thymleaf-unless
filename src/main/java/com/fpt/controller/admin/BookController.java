package com.fpt.controller.admin;

import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.entity.Category;
import com.fpt.entity.Publisher;
import com.fpt.service.admin.AuthorServiceImpl;
import com.fpt.service.admin.BookServiceImpl;
import com.fpt.service.admin.CategoryServiceImpl;
import com.fpt.service.admin.PublisherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @Autowired
    AuthorServiceImpl authorService;

    @Autowired
    PublisherServiceImpl publisherService;

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/book/list";
    }

    @GetMapping(value = "/show_single/{id}")
    public String detail(@PathVariable long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "admin/book/detail";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("book", new Book());
        List<Author> authors = authorService.findAll();
        List<Publisher> publishers = publisherService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);
        model.addAttribute("categories", categories);
        return "admin/book/create";
    }

    @PostMapping(value = "/create")
    public String store(@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/book/create";
        }
        bookService.save(book);
        return "redirect:/books/list";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam(value = "name", required = false) String name, Model model) {
        if (StringUtils.isEmpty(name)) {
            return "redirect:/authors/list";
        }

        model.addAttribute("books", bookService.search((name)));
        return "admin/books/list";
    }

    @GetMapping(value = "/update/{id}")
    public String updateBook(@PathVariable long id, Model model) {
        Book book = bookService.getById(id);

        List<Author> authors = authorService.findAll();
        List<Publisher> publishers = publisherService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);
        model.addAttribute("categories", categories);
        model.addAttribute("book", book);

        return "admin/book/edit";
    }

    @PostMapping(value = "update/{id}")
    public String update(@PathVariable long id, Book book) {
        bookService.update(id, book);
        return "redirect:/books/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        bookService.delete(id);
        redirectAttributes.addFlashAttribute("Success!", "Deleted contact successfully!");
        return "redirect:/books/list";
    }

}
