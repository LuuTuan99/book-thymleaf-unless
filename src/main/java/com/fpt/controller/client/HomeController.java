package com.fpt.controller.client;

import com.fpt.service.admin.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    BookServiceImpl bookService;
    //demo shop index
    @RequestMapping(method = RequestMethod.GET)
    public String shop_product(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "client/shop-index";
    }

    //demo shop list item
    @GetMapping(value = "/shop-product-list")
    public String shop_product_list(Model model) {
        model.addAttribute("productBook",bookService.findAll());
        return "client/shop-product-list";
    }

    //demo shop list item
    @GetMapping(value = "/shop-search-result")
    public String shop_search_result() {
        return "client/shop-search-result";
    }

    //demo shop shopping cart
    @GetMapping(value = "/shop-shopping-cart")
    public String shop_shopping_cart() {
        return "client/shop-shopping-cart";
    }

    //demo shop shopping cart null
    @GetMapping(value = "/shop-shopping-cart-null")
    public String shop_shopping_cart_null() {
        return "client/shop-shopping-cart-null";
    }

    //demo shop item
    @GetMapping(value = "/shop-item")
    public String shop_item() {
        return "client/shop-item";
    }
}


