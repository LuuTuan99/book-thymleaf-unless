package com.fpt.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    //demo shop index
    @GetMapping(value = "/index")
    public String shop_index(){
        return "client/shop-index";
    }

    //demo shop list item
    @GetMapping(value = "/shop-product-list")
    public String shop_product_list(){
        return "client/shop-product-list";
    }

    //demo shop list item
    @GetMapping(value = "/shop-search-result")
    public String shop_search_result(){
        return "client/shop-search-result";
    }

    //demo shop shopping cart
    @GetMapping(value = "/shop-shopping-cart")
    public String shop_shopping_cart(){
        return "client/shop-shopping-cart";
    }

    //demo shop shopping cart null
    @GetMapping(value = "/shop-shopping-cart-null")
    public String shop_shopping_cart_null(){
        return "client/shop-shopping-cart-null";
    }

    //demo shop item
    @GetMapping(value = "/shop-item")
    public String shop_item(){
        return "client/shop-item";
    }

    //demo page login
    @GetMapping(value = "/login")
    public String login(){
        return "client/login-register/page-login";
    }

    //demo page register
    @GetMapping(value = "/register")
    public String register(){
        return "client/login-register/page-register";
    }

    //demo page client2
    @GetMapping(value = "/demo3")
    public String demo3(){
        return "client2/product";
    }
    //demo page client2
    @GetMapping(value = "/demo4")
    public String demo4(){
        return "client2/cart";
    }
}
