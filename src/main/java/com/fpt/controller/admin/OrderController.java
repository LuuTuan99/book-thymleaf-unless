package com.fpt.controller.admin;


import com.fpt.entity.OrderBook;
import com.fpt.service.admin.order.OrderDetailsService;
import com.fpt.service.admin.order.OrderService;
import com.fpt.service.admin.order.OrderServiceImplement;
import com.fpt.specification.BookSpecification;
import com.fpt.specification.OrderSpecification;
import com.fpt.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/admin/orders")
public class OrderController {
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderServiceImplement orderServiceImplement;
    @RequestMapping(method = RequestMethod.GET)
    public String listOrder(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            Model model){
        Specification specification = Specification.where(null);
        if (keyword != null && keyword.length() > 0) {
            specification = specification
                    .and(new OrderSpecification(new SearchCriteria("keyword", "join", keyword)));
            model.addAttribute("keyword", keyword);
        }

        Page<OrderBook> orderBookPage = orderServiceImplement.findAllActive(specification, PageRequest.of(page - 1, limit));
        model.addAttribute("orderBooks", orderBookPage.getContent());
        double total =SumPrice();
        double total1 =getUnitPriceOnWeek();
        model.addAttribute("list2",orderDetailsService.fillAll());
        // model.addAttribute("list",orderService.findAll());
        model.addAttribute("order",new OrderBook());
        model.addAttribute("total",total);

        model.addAttribute("total1",total1);


        model.addAttribute("currentPage", orderBookPage.getPageable().getPageNumber() + 1);
        model.addAttribute("limit", orderBookPage.getPageable().getPageSize());
        model.addAttribute("totalPage", orderBookPage.getTotalPages());


        return "/admin/order/list";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable long id, Model model) {
        OrderBook order = orderService.getById(id);
        if (order == null) {
            return "error/404";
        }
        model.addAttribute("order", order);
        return "/admin/order/detail";
    }

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

    public double getUnitPriceOnWeek(){
        List<OrderBook> orderBookListSuccess =orderService.findByStatus(0);
        LocalDate currentTime = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault()).toLocalDate();
        double total=0;
        for (OrderBook item :orderBookListSuccess){
            Period different = Period.between(Instant.ofEpochMilli(item.getCreatedAt()).atZone(ZoneId.systemDefault()).toLocalDate(), currentTime);
            if (different.getDays()<=7){
                total +=item.getUnitPrice();
            }
        }
        return total;
    }

}
