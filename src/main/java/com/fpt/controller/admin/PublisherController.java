package com.fpt.controller.admin;

import com.fpt.config.ProjectConfig;
import com.fpt.entity.Book;
import com.fpt.entity.Publisher;
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
            Model model) {
        Page<Publisher> publisherPage = publisherService.findAll(PageRequest.of(page - 1, limit));
        model.addAttribute("publishers", publisherPage.getContent());
        model.addAttribute("currentPage", publisherPage.getPageable().getPageNumber() + 1);
        model.addAttribute("limit", publisherPage.getPageable().getPageSize());
        model.addAttribute("totalPage", publisherPage.getTotalPages());
        return "admin/publisher/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable long id, Model model) {
        Publisher publisher = publisherService.getById(id);

        if (publisher == null) {
            return "error/404";
        }
        Set<Book> books = publisher.getBooks();
        model.addAttribute("publisher", publisher);
        model.addAttribute("books", books);
        return "admin/publisher/detail";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "admin/publisher/create";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String store(@Valid Publisher publisher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/publisher/create";
        }
        publisherService.save(publisher);
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_PUBLISHERS;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (StringUtils.isEmpty(keyword)) {
            return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_PUBLISHERS;
        }

        model.addAttribute("publishers", publisherService.search((keyword)));
        return "admin/publisher/list";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = false) long id, RedirectAttributes redirectAttributes) {
        publisherService.delete(id);
        redirectAttributes.addFlashAttribute("Success!", "Deleted contact successfully!");
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_PUBLISHERS;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update/{id}")
    public String updatePublisher(@PathVariable long id, Model model) {
        Publisher publisher = publisherService.getById(id);
        model.addAttribute("publisher", publisher);
        return "admin/publisher/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
    public String update(@PathVariable long id, Publisher publisher) {
        publisherService.update(id, publisher);
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_PUBLISHERS;
    }
}
