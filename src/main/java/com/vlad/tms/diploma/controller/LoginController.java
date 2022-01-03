package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.service.ProductService;
import com.vlad.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping ("/login")
    public String loginPage (Model model){
        model.addAttribute("search", productService.searchInput());
        return "login";
    }

    @GetMapping ("/login/forgotPassword")
    public String forgotPasswordPage(Model model){
        model.addAttribute("search", productService.searchInput());
        return "forgotPassword";
    }

    @PostMapping ("/login/forgotPassword")
    public String forgotPasswordForm (@RequestParam ("email") String email,
                                      Model model){

        if(userService.findEmail(email) == null){
            model.addAttribute("search", productService.searchInput());
            model.addAttribute("errorFindEmail", "Такой E-mail у нас не был зарегистрирован");
            return "forgotPassword";
        } else{
            User user = userService.findEmail(email);
            userService.sendMessageRestorePass(user);
            model.addAttribute("search", productService.searchInput());
            model.addAttribute("errorFindEmail", "Вам на почту была отправлена ссылка на восстановление пароля");
            return "forgotPassword";
        }
    }

    @GetMapping("/restore/{code}")
    public String activate(Model model, @PathVariable String code) {

        model.addAttribute("search", productService.searchInput());
        model.addAttribute("code", code);
        return "updatePassword";
    }


    @PostMapping ("/restore")
    public String passwordUpdate(@RequestParam ("code") String code,
                                 @RequestParam ("password") String password,
                                 Model model){
        User user = userService.findByCode(code);
        user.setPassword(passwordEncoder.encode(password));
        user.setActivationCode(null);
        userService.save(user);

        model.addAttribute("search", productService.searchInput());
        model.addAttribute("passUpdate", "Пароль успешно обновлен");
        return "login";
    }
}
