package com.vlad.tms.diploma.controller;


import com.vlad.tms.diploma.model.order.DataOrder;
import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.model.product.Product;
import com.vlad.tms.diploma.service.CustomerService;
import com.vlad.tms.diploma.service.DataOrderService;
import com.vlad.tms.diploma.service.OrderItemService;
import com.vlad.tms.diploma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DataOrderService dataOrderService;
    @Autowired
    private OrderItemService orderItemService;



    @GetMapping()
    public String catalogPage(Model model){
        model.addAttribute("productList", productService.findAll());
        return "catalog";
    }

    @GetMapping ("/{id}")
    public String addInBasket (@PathVariable("id") Long id, Model model){
        orderItemService.addOrder(id);
        model.addAttribute("product", productService.findById(id));
        return "redirect:/catalog";
    }

}
