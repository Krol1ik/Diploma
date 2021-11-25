package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryOrderController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping ("/history")
    public String historyOrder(@AuthenticationPrincipal User user, Model model){

        model.addAttribute("dataOrder", orderItemService.historyOrderForUser(user));
        return "authenticatedUser/historyOrder";
    }
}
