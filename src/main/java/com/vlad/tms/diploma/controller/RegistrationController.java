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
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid Address address, BindingResult errorAddress,
                          @RequestParam("cityName") String cityName,
                          @ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors() || errorAddress.hasErrors()){
            return "/registration";
        }
        if (cityService.getCity(cityName) == null || cityName.isEmpty()) {
            model.addAttribute("messagesErrorCity", "Некорректно указан город");
            return "/registration";

        } else if(userService.findEmail(user.getEmail()) != null) {
                model.addAttribute("messagesForEmail", "Такой e-mail уже существует");
                return "registration";

        } else if (!userService.addUser(user, cityService.getCity(cityName), address)) {
            model.addAttribute("messages", "Такой логин уже существует");
            return "registration";
        }else {
            return "redirect:/login";
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

            model.addAttribute("messagesActiv", "Активация прошла успешна");

        return "login";
    }
}

