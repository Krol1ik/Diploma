package com.vlad.tms.diploma.controller;


import com.vlad.tms.diploma.service.CategoryService;
import com.vlad.tms.diploma.service.OrderItemService;
import com.vlad.tms.diploma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping()
    public String catalogPage(Model model){
        model.addAttribute("productList", productService.findAll());
        model.addAttribute("categoryList", categoryService.categoryAll());
        return "catalog";
    }

    @GetMapping("/category/{id}")
    public String catalogOnCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute("productList", productService.findByCategory(id));
        model.addAttribute("categoryList", categoryService.categoryAll());
        return "catalog";
    }

    @GetMapping ("/{id}")
    public String addInBasket (@PathVariable("id") Long id, Model model){
        orderItemService.addOrder(id);
        model.addAttribute("product", productService.findById(id));
        return "redirect:/catalog";
    }

}
