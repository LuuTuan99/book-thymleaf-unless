package com.fpt.controller.client;


import com.fpt.entity.Item;
import com.fpt.entity.OrderBook;
import com.fpt.entity.OrderDetails;
import com.fpt.entity.OrderDetailsId;
import com.fpt.service.admin.BookService;
import com.fpt.service.admin.MemberService;
import com.fpt.service.admin.order.OrderDetailsService;
import com.fpt.service.admin.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class ShoppingCartController {
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private MemberService accountService;


    //List card
    @GetMapping(value = "/listCard")
    public String index(Model model,HttpSession session){
        model.addAttribute("total",totalPrice(session));
        System.out.println("Gia theo phien: "+totalPrice(session));
        return "client/shop-shopping-cart";
    }


    //Update số lượng sách order mỗi loại.
    @PostMapping(value = "/update")
    public String update(HttpServletRequest request, HttpSession session){

        String[] quantities = request.getParameterValues("quantity");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for (int i=0;i<cart.size();i++){
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart",cart);
        return "redirect:/cart/listCard";
    }


    //Xóa giỏ hàng(Xóa order của mỗi cuốn sách riêng)
    @GetMapping(value = "/remove/{id}")
    public String deleteCart(@PathVariable("id") long id, HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index =isExists(id,cart);
        cart.remove(index);
        session.setAttribute("cart",cart);
        return "redirect:/cart/listCard";
    }


    //Thêm sách vào giỏ hàng
    @GetMapping(value = "/buy/{id}")
    public String addCart(@PathVariable("id") long id,
                          @RequestParam(name = "quantity", defaultValue = "1") int quantity
            ,HttpSession session,RedirectAttributes redirectAttributes){
        if(session.getAttribute("cart") == null){
            List<Item> cart = new ArrayList<>();
            cart.add(new Item(bookService.getById(id),quantity));
            session.setAttribute("cart",cart);
        }else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index =isExists(id,cart);
            if(index==-1){
                cart.add(new Item(bookService.getById(id),1));

            }else {
                quantity =cart.get(index).getQuantity() +1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart",cart);
        }
        redirectAttributes.addFlashAttribute("success","Thêm thành công sách vào giỏ hàng");
        return "redirect:/shop-item/{id}";
    }

    @GetMapping(value = "/checkout")
    public String showForm(Model model){
        model.addAttribute("order",new OrderBook());
        return "checkout";
    }
    @PostMapping(value = "/checkout")
    public String checkOut(Authentication authentication, HttpSession session,OrderBook order){
        // add new orders;


        if(authentication ==null){
            return "redirect:/account/login";
        }else {
            if(session.getAttribute("cart") !=null){
                order.setCreatedBy(accountService.getByUsername(authentication.getName()));
                order.setNameOrder("Order by account: "+authentication.getName());
                order= orderService.create(order);
                //save orderdetails
                List<Item> cart = (List<Item>) session.getAttribute("cart");
                for (Item item :cart){
                    OrderDetails details = new OrderDetails();
                    details.setId(new OrderDetailsId(order.getId(),item.getBook().getId()));
                    details.setBookName(item.getBook().getName());
                    details.setPrice(item.getBook().getPrice());
                    details.setQuantity(item.getQuantity());
                    orderDetailsService.create(details);
                }
                //remove cart
                session.removeAttribute("cart");
            }
            return "orders/thanks";
        }

    }

    //kiểm tra tồn tại của 1 cuốn sách đã order trong cart
    private int isExists(long id,List<Item> cart){
        for (int i=0;i<cart.size();i++){
            if(cart.get(i).getBook().getId() ==id){
                return i;
            }
        }
        return -1;
    }
    //Tổng tiền của 1 cart.
    private double totalPrice(HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double total=0;
        if(cart==null){
            total=0;
        }else {
            for (Item item :cart){
                total += item.getQuantity() * item.getBook().getPrice();
            }
        }
        return total;
    }

}
