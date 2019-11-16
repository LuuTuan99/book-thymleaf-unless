package com.fpt.controller;


import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.entity.Publisher;
import com.fpt.service.PublisherServiceImpl;
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
@RequestMapping(value = "/publishers")
public class PublisherController {
    @Autowired
    PublisherServiceImpl publisherService;

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("publishers", publisherService.findAll());
        return "";
    }
//
//    @GetMapping(value = "/{id}")
//    public String getDetail(@PathVariable long id, Model model) {
//        model.addAttribute("publisher", publisherService.getById(id));
//        return "";
//    }

//    @GetMapping(value = "/publisher/books/{id}")
//    public String showBooksByPublisher(@PathVariable long publisher_id, Model model) {
//        Publisher publisher = publisherService.getById(publisher_id);
//        Set<Book> books = publisher.getBooks();
//        model.addAttribute("publisher", publisher);
//        model.addAttribute("books", books);
//        return "";
//    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "";
    }

    @PostMapping(value = "/create")
    public String store(@Valid Publisher publisher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "";
        }
        publisherService.save(publisher);
        return "redirect:/publishers/list";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam(value = "name", required = false) String name, Model model) {
        if (StringUtils.isEmpty(name)) {
            return "redirect:/publishers/list";
        }

        model.addAttribute("publishers", publisherService.search((name)));
        return "";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = false) long id, RedirectAttributes redirectAttributes) {
        publisherService.delete(id);
        redirectAttributes.addFlashAttribute("Success!", "Deleted contact successfully!");
        return "redirect:/publishers/list";
    }

    @GetMapping(value = "/update/{id}")
    public String updateAuthor(@PathVariable long id, Model model) {
        Publisher publisher = publisherService.getById(id);
        model.addAttribute("publisher", publisher);
        return "";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable long id, Publisher publisher) {
        publisherService.update(id, publisher);
        return "redirect:/publishers/list";
    }
}
