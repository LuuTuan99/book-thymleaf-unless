package com.fpt.controller.admin;

import com.fpt.entity.OrderBook;
import com.fpt.service.admin.order.OrderDetailsService;
import com.fpt.service.admin.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.fpt.config.ProjectConfig;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrderController {
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private OrderService orderService;
    @RequestMapping(method = RequestMethod.GET)
    public String listOrder(Model model){

        model.addAttribute("list2",orderDetailsService.fillAll());
        model.addAttribute("list",orderService.findAll());
        return "order/test";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable long id, Model model) {
        OrderBook order = orderService.getById(id);
        if (order == null) {
            return "error/404";
        }
        model.addAttribute("order", order);
        return "order/detail";
    }


}
