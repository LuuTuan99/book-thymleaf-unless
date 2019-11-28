package com.fpt.controller.admin;

import com.fpt.entity.Book;
import com.fpt.entity.OrderBook;
import com.fpt.service.admin.BookService;
import com.fpt.service.admin.order.OrderDetailsService;
import com.fpt.service.admin.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
=======
import org.springframework.web.bind.annotation.PathVariable;

import com.fpt.config.ProjectConfig;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
>>>>>>> 835c72d12fe5c4a31a5b2bc55f8877e83b1fc0c7

@Controller
@RequestMapping(value = "/admin/orders")
public class OrderController {
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private OrderService orderService;
    @RequestMapping(method = RequestMethod.GET)
    public String listOrder(Model model){
        double total =SumPrice();
        model.addAttribute("list2",orderDetailsService.fillAll());
        model.addAttribute("list",orderService.findAll());
        model.addAttribute("order",new OrderBook());
        model.addAttribute("total",total);


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

<<<<<<< HEAD
    @PostMapping(value = "/updateStatus/{orderId}")
    /*@ResponseBody*/
    public String updateStatus(@PathVariable long orderId, HttpServletRequest request){
        int status = Integer.parseInt(request.getParameter("status"));
        System.out.println("Trang thai moi la:"+status);
        System.out.println("OrderID:" +orderId);
        OrderBook orderBookExist = orderService.getById(orderId);
        orderBookExist.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        orderBookExist.setStatus(status);
        orderService.update(orderId,orderBookExist);
        return "redirect:/admin/orders";
    }

    public double SumPrice(){
        List<OrderBook> orderBookListSuccess =orderService.findByStatus(0);
        double totalPrice =0;
        for (int i=0;i<orderBookListSuccess.size();i++){
            totalPrice+=orderBookListSuccess.get(i).getUnitPrice();
        }
        return totalPrice;
    }
=======

>>>>>>> 835c72d12fe5c4a31a5b2bc55f8877e83b1fc0c7
}
