package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.address.Address;
import com.vlad.tms.diploma.model.address.City;
import com.vlad.tms.diploma.model.entity.Customer;
import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.service.CityService;
import com.vlad.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("cityList", cityService.allCity());
        model.addAttribute("customer", new User());
        model.addAttribute("address", new Address());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Address address,
                          @RequestParam("cityName") String cityName,
            @ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        if (!userService.addUser(user, cityService.getCity(cityName), address)) {
            model.addAttribute("messages", "User exist!");
            return "registration";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messages", "Activation is successful");
        } else {
            model.addAttribute("messages", "Activation code is not found");
        }
        return "login";
    }
}

