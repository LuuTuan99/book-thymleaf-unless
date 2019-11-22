package com.fpt.controller.admin;

import com.fpt.config.ProjectConfig;
import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.entity.Category;
import com.fpt.entity.Publisher;
import com.fpt.service.admin.AuthorServiceImpl;
import com.fpt.service.admin.BookServiceImpl;
import com.fpt.service.admin.CategoryServiceImpl;
import com.fpt.service.admin.PublisherServiceImpl;
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
import java.util.List;


@Controller
@RequestMapping(value = ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_BOOKS)
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @Autowired
    AuthorServiceImpl authorService;

    @Autowired
    PublisherServiceImpl publisherService;

    @Autowired
    CategoryServiceImpl categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            Model model) {
        Page<Book> bookPage = bookService.findAll(PageRequest.of(page - 1, limit));
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        model.addAttribute("categories", categoryService.findAll());

        model.addAttribute("currentPage", bookPage.getPageable().getPageNumber() + 1);
        model.addAttribute("limit", bookPage.getPageable().getPageSize());
        model.addAttribute("totalPage", bookPage.getTotalPages());
        return "admin/book/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable long id, Model model) {
        Book book = bookService.getById(id);
        if (book == null) {
            return "404";
        }
        model.addAttribute("book", book);
        return "admin/book/detail";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
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

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String store(@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/book/create";
        }
        bookService.save(book);
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_BOOKS;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public String search(@RequestParam(value = "name", required = false) String name, Model model) {
        if (StringUtils.isEmpty(name)) {
            return "redirect:/authors/list";
        }

        model.addAttribute("books", bookService.search((name)));
        return "admin/books/list";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        bookService.delete(id);
        redirectAttributes.addFlashAttribute("Success!", "Deleted contact successfully!");
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_BOOKS;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update/{id}")
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

    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
    public String update(@PathVariable long id, Book book) {
        bookService.update(id, book);
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_BOOKS;
    }


}
