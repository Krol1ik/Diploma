package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.address.Address;
import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.service.AddressService;
import com.vlad.tms.diploma.service.CityService;
import com.vlad.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class ProfileController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", user);
        model.addAttribute("cityUser", user.getAddress().getCity().getCityName());
        model.addAttribute("cityList", cityService.allCity());
        return "authenticatedUser/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @Valid Address address, BindingResult bindingResultAddress,
                                @RequestParam(value = "cityName") String cityName,
                                @ModelAttribute ("user") @Valid User userUpdate, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors() || bindingResultAddress.hasErrors()) {
            return "authenticatedUser/profile";
        } else if (cityService.getCity(cityName) == null || cityName.isEmpty()) {
            model.addAttribute("messagesErrorCity", "Некорректно указан город");
            return "authenticatedUser/profile";
        } else {
            userUpdate.getAddress().setCity(cityService.getCity(cityName));
            userService.updateProfile(user, userUpdate);
            return "authenticatedUser/profile";
        }
    }
}
