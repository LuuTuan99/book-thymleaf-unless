package com.fpt.controller.admin;

import com.fpt.config.ProjectConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_ORDER)
public class OrderController {

   @RequestMapping(method = RequestMethod.GET)
    public String orderList(){
       return "admin/order/list";
   }
}
