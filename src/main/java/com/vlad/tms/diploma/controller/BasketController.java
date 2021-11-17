package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.service.DataOrderService;
import com.vlad.tms.diploma.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private DataOrderService dataOrderService;

    @GetMapping
    public String basketPage(Model model) {
        model.addAttribute("order", orderItemService.placedOrder());
        return "basket";
    }

    @PostMapping
    public String placedOrder (@RequestParam ("orderId") List<OrderItem> orderItem,
                               @RequestParam ("orderCount") List<Integer> count){

        for (int i = 0; i < orderItem.size(); i++) {
            orderItem.get(i).setCount(count.get(i));

        }
        dataOrderService.saveOrder(orderItem);
        return "redirect:basket";
    }
}
