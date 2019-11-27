package com.fpt.controller.client;


import com.fpt.entity.Book;
import com.fpt.repository.BookRepository;
import com.fpt.service.admin.AuthorServiceImpl;
import com.fpt.service.admin.BookServiceImpl;
import com.fpt.service.admin.CategoryServiceImpl;
import com.fpt.service.admin.PublisherServiceImpl;
import com.fpt.specification.BookSpecification;
import com.fpt.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookServiceImpl bookService;
  
    @Autowired
    AuthorServiceImpl authorService;

    @Autowired
    PublisherServiceImpl publisherService;

    @Autowired
    CategoryServiceImpl categoryService;
    //demo shop index
    /* @RequestMapping(method = RequestMethod.GET)
    public String shop_product(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "client/shop-index";
    } */

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listBooks(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        List<Book> books = bookRepository.findAll(Sort.by(Sort.Direction.DESC,"updatedAtMLS"));

        model.addAttribute("bookList", books);

        List<Book> fourBooks = new ArrayList<>();
        for (int i= 0; i < 5;i++){
            Book book = books.get(i);
            fourBooks.add(book);
        }
        model.addAttribute("fourBooks",fourBooks);
        model.addAttribute("bookPage", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "client/shop-index";
    }

    //demo shop list item
    @RequestMapping(method = RequestMethod.GET,value = "/shop-product-list")
    public String shop_product_list(
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            Model model) {
        Specification specification = Specification.where(null);

        if (categoryId != null && categoryId > 0) {
            specification = specification
                    .and(new BookSpecification(new SearchCriteria("categoryId", "joinCategory", categoryId)));
            model.addAttribute("categoryId", categoryId);
        }

        if (keyword != null && keyword.length() > 0) {
            specification = specification
                    .and(new BookSpecification(new SearchCriteria("keyword", "join", keyword)));
            model.addAttribute("keyword", keyword);
        }
        Page<Book> bookPage = bookService.findAllActive(specification, PageRequest.of(page - 1, limit));

        model.addAttribute("books", bookPage.getContent());

        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPage", bookPage.getPageable().getPageNumber() + 1);
        model.addAttribute("limit", bookPage.getPageable().getPageSize());
        model.addAttribute("totalPage", bookPage.getTotalPages());
        return "client/shop-product-list";
    }

    // shop list item page
    @GetMapping(value = "/shop-search-result")
    public String shop_search_result() {
        return "client/shop-search-result";
    }

    // shop shopping cart page
    @GetMapping(value = "/shop-shopping-cart")
    public String shop_shopping_cart() {
        return "client/shop-shopping-cart";
    }

    // shop shopping cart null page
    @GetMapping(value = "/shop-shopping-cart-null")
    public String shop_shopping_cart_null() {
        return "client/shop-shopping-cart-null";
    }

    //shop item page
    @RequestMapping(method = RequestMethod.GET, value = "shop-item/{id}")
    public String detail(@PathVariable long id, Model model) {
        Book book = bookService.getById(id);
        if (book == null) {
            return "error/404";
        }
        model.addAttribute("book", book);
        return "client/shop-item";
    }

//    @GetMapping(value = "/shop-item")
//    public String shop_item() {
//        return "client/shop-item";
//    }

    //shop account page
    @GetMapping(value = "/shop-checkout")
    public String shop_checkout(){
        return "client/shop-checkout";
    }

    //shop account page
    @GetMapping(value = "/shop-account")
        public String shop_account(){
        return "client/shop-account";
        }
}


