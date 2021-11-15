package com.vlad.tms.diploma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @GetMapping
    public String basketPage() {

        return "basket";
    }
}
