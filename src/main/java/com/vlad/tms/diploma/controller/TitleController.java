package com.vlad.tms.diploma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TitleController {

    @GetMapping
    public String titlePage() {
        return "title";
    }

}
