package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TitleController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String titlePage(Model model){
        model.addAttribute("categoryList", categoryService.categoryAll());
        return "title";
    }

}
