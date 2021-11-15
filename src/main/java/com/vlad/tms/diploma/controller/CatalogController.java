package com.vlad.tms.diploma.controller;


import com.vlad.tms.diploma.model.product.Product;
import com.vlad.tms.diploma.service.CustomerService;
import com.vlad.tms.diploma.service.DataOrderService;
import com.vlad.tms.diploma.service.OrderItemService;
import com.vlad.tms.diploma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

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



    @GetMapping
    public String catalogPage(Model model){
        List<Product> productList = productService.findAll();
        model.addAttribute("product", productList);
        return "catalog";
    }

    @PostMapping
    public String addInBasket(@ModelAttribute ("product") Product product,
                              Model model) {

        return "catalog";
    }
}
