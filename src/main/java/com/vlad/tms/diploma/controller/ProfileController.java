package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.address.Address;
import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.service.CityService;
import com.vlad.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddress());
        model.addAttribute("cityUser", user.getAddress().getCity().getCityName());
        model.addAttribute("cityList", cityService.allCity());
        return "authenticatedUser/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @ModelAttribute("address") @Valid Address address, BindingResult bindingResultAddress,
                                @RequestParam(value = "cityName") String cityName,
                                @ModelAttribute("user") @Valid User userUpdate,BindingResult bindingResult, Model model) {



        if (bindingResult.hasErrors() || bindingResultAddress.hasErrors()) {
            return "authenticatedUser/profile";
        } else if (!user.getEmail().equals(userUpdate.getEmail())) {
            model.addAttribute("messagesForEmail", "Чтобы изменить e-mail нажмите на кнопку рядом");
            return "authenticatedUser/profile";
        }else if (cityService.getCity(cityName) == null || cityName.isEmpty()) {
            model.addAttribute("messagesErrorCity", "Некорректно указан город");
            return "authenticatedUser/profile";
        } else if (!userService.updateProfile(user, userUpdate, address, cityName)) {
            model.addAttribute("messages", "Такой логин уже существует");
            return "authenticatedUser/profile";
        } else {
            return "authenticatedUser/updateComplete";
        }
    }

    @GetMapping ("/changeEmail")
    public String updateEmailForm (@AuthenticationPrincipal User user) {
        userService.sendMessageRestoreEmail(user);
        return "authenticatedUser/checkEmailOnLink";
    }

    @GetMapping("/restoreEmail/{code}")
    public String updateEmail(@PathVariable String code, Model model) {
        model.addAttribute("code", code);
        return "authenticatedUser/updateEmail";
    }

    @GetMapping("/updateEmail")
    public String updateProfileEmailGet(@RequestParam ("code") String code, Model model) {
        System.out.println(code);
        model.addAttribute("code", code);
        return "authenticatedUser/updateEmail";
    }

    @PostMapping ("/updateEmail")
    public String updateProfileEmail(@RequestParam ("code") String code,
                                     @RequestParam ("email") String email,
                                     Model model){

        User user = userService.findByCode(code);

        if(user.getEmail().equals(email)){
            model.addAttribute("code", code);
            model.addAttribute("errorEmail", "Ваш аккаунт уже зарегестрирован на e-mail: " + email);
            return "authenticatedUser/updateEmail";
        } else if (userService.findEmail(email) != null){
            model.addAttribute("code", code);
            model.addAttribute("errorEmail", "Данный e-mail уже занят, введите другой");
            return "authenticatedUser/updateEmail";
        } else {
            user.setEmail(email);
            user.setActivationCode(null);
            userService.save(user);
            return "authenticatedUser/updateComplete";
        }
    }
}

