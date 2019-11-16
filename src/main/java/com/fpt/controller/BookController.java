package com.fpt.controller;

import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.service.AuthorServiceImpl;
import com.fpt.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @Autowired
    AuthorServiceImpl authorService;

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/showList";
    }

    @GetMapping(value = "/book/{id}")
    public String getDetail(@PathVariable long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "books/detail";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("book", new Book());
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "books/form";
    }

    @PostMapping(value = "/create")
    public String store(@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/books/form";
        }
        bookService.save(book);
        return "redirect:/books/list";
    }

}
