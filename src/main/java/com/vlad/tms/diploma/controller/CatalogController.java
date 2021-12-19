package com.vlad.tms.diploma.controller;


import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.service.CategoryService;
import com.vlad.tms.diploma.service.OrderItemService;
import com.vlad.tms.diploma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public String catalogPage(Model model) {

        model.addAttribute("productList", productService.findAllProduct());
        model.addAttribute("categoryList", categoryService.categoryAll());
        return "catalog";
    }

    @GetMapping("/category/{id}")
    public String catalogOnCategory(@PathVariable("id") Long id, Model model) {
        model.addAttribute("productList", productService.findByCategory(id));
        model.addAttribute("categoryList", categoryService.categoryAll());
        return "catalog";
    }

    @PostMapping("/addInBasket")
    public String addInBasket(@RequestParam ("idProd") Long id,
                              HttpSession session,
                              Model model) {
        String sessionId = session.getId();
        orderItemService.addOrder(id, sessionId);
        return "redirect:/catalog";
    }


    @Transactional
    @GetMapping ("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.delete(id);
        return "redirect:/catalog";
    }

    @PostMapping ("/addInBasketForUser")
    public String addInBasketForUser (@AuthenticationPrincipal User user,
                                      @RequestParam ("idProd") Long id){
        orderItemService.addOrderForUser(id, user);
        return "redirect:/catalog";
    }

}


