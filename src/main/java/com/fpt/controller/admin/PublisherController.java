package com.fpt.controller.admin;

import com.fpt.config.ProjectConfig;
import com.fpt.entity.Book;
import com.fpt.entity.Publisher;
import com.fpt.service.admin.PublisherServiceImpl;
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
@RequestMapping(value = ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_PUBLISHERS)
public class PublisherController {
    @Autowired
    PublisherServiceImpl publisherService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            Model model
    ) {
        model.addAttribute("publishers", publisherService.findAll());
        return "admin/publisher/list";
    }

    @GetMapping(value = "/by_books/{id}")
    public String detail(@PathVariable long id, Model model) {
        Publisher publisher = publisherService.getById(id);
        Set<Book> books = publisher.getBooks();
        model.addAttribute("publisher", publisher);
        model.addAttribute("books", books);
        return "admin/publisher/detail";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "admin/publisher/create";
    }

    @PostMapping(value = "/create")
    public String store(@Valid Publisher publisher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/publisher/create";
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
        return "admin/publisher/list";
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
        return "admin/publisher/edit";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable long id, Publisher publisher) {
        publisherService.update(id, publisher);
        return "redirect:/publishers/list";
    }
}
