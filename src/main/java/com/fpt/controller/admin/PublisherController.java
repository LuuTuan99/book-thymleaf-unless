package com.fpt.controller.admin;

import com.fpt.config.ProjectConfig;
import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.entity.Publisher;
import com.fpt.service.admin.PublisherServiceImpl;
import com.fpt.specification.AuthorSpecification;
import com.fpt.specification.PublisherSpecification;
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
@RequestMapping(value = ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_PUBLISHERS)
public class PublisherController {
    @Autowired
    PublisherServiceImpl publisherService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            Model model) {
        Specification specification = Specification.where(null);
        if (keyword != null && keyword.length() > 0) {
            specification = specification
                    .and(new PublisherSpecification(new SearchCriteria("name", "=", keyword)))
                    .or(new PublisherSpecification(new SearchCriteria("description", "=", keyword)));
        }
        Page<Publisher> publisherPage = publisherService.findAllActive(specification,PageRequest.of(page - 1, limit));
        model.addAttribute("keyword", keyword);

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

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable(value = "id", required = false) long id, HttpServletResponse response) {
        Publisher publisher = publisherService.getById(id);
        if (publisher == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new Gson().toJson("Error");
        }
        publisherService.delete(id);
        response.setStatus(HttpStatus.OK.value());
        return new Gson().toJson("Ok");
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
