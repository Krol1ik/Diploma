package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.service.DataOrderService;
import com.vlad.tms.diploma.service.OrderItemService;
import com.vlad.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping ("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderItemService orderItemService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping ("/userList")
    public String adminPage(Model model){

        List<User> userList = userService.findAll();

        List<Double> priceOrderList = new ArrayList<>();
        Double priceOrders = 0.0;
        List<Integer> countProductList = new ArrayList<>();
        int countProduct = 0;
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            List<OrderItem> orderItems = orderItemService.historyOrderForUser(user);
            for (int j = 0; j < orderItems.size(); j++) {
                priceOrders = priceOrders + orderItems.get(j).getPriceOrder();
                countProduct = countProduct + orderItems.get(j).getCount();
            }
            priceOrderList.add(priceOrders);
            countProductList.add(countProduct);
            priceOrders = 0.0;
            countProduct = 0;
       }

        model.addAttribute("priceOrder", priceOrderList);
        model.addAttribute("countOrder", countProductList);
        model.addAttribute("user", userService.findAll());
        return "admin/userList";
    }
}
