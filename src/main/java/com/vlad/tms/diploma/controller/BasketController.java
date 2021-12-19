package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.address.Address;
import com.vlad.tms.diploma.model.entity.Customer;
import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.service.CityService;
import com.vlad.tms.diploma.service.DataOrderService;
import com.vlad.tms.diploma.service.MailSenderService;
import com.vlad.tms.diploma.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class BasketController {

    @Autowired
    private CityService cityService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private DataOrderService dataOrderService;



    @GetMapping("/basket")
    public String basketPage(HttpSession session ,Model model) {
        String sessionId = session.getId();
        model.addAttribute("order", orderItemService.placedOrder(sessionId));
        return "basket";
    }


    @PostMapping("/basket")
    public String placedOrder(@AuthenticationPrincipal User user,
                              @RequestParam(value = "orderId", required = false) List<OrderItem> orderItem,
                              @RequestParam(value = "orderCount", required = false) List<Integer> count,
                              HttpSession session,
                              Model model) {

        String sessionId = session.getId();

        if (orderItem == null) {
            model.addAttribute("messages", "У вас еще нет товаров в корзине");
            return "basket";
        }
        for (int i = 0; i < orderItem.size(); i++) {
            orderItem.get(i).setCount(count.get(i));
            orderItemService.saveOrder(orderItem.get(i));
        }
        for (int i = 0; i < orderItemService.placedOrder(sessionId).size(); i++) {
            if(orderItemService.placedOrder(sessionId).get(i).getProductOrder().getStockBalance() < orderItemService.placedOrder(sessionId).get(i).getCount()){
                model.addAttribute("order", orderItemService.placedOrder(sessionId));
                model.addAttribute("errorCount", "Доступно только " +
                        orderItemService.placedOrder(sessionId).get(i).getProductOrder().getStockBalance() + " товара под артикулом: " +
                        orderItemService.placedOrder(sessionId).get(i).getProductOrder().getArticle());
                return "/basket";
            }
        }
        for (int i = 0; i < orderItemService.placedOrderForUser(user).size(); i++) {
            if(orderItemService.placedOrderForUser(user).get(i).getProductOrder().getStockBalance() < orderItemService.placedOrderForUser(user).get(i).getCount()){
                model.addAttribute("orderForUser", orderItemService.placedOrderForUser(user));
                model.addAttribute("errorCountForUser", "Доступно только " +
                        orderItemService.placedOrderForUser(user).get(i).getProductOrder().getStockBalance() + " товара под артикулом: " +
                        orderItemService.placedOrderForUser(user).get(i).getProductOrder().getArticle());
                return "/basket";
            }
        }
        if (user == null) {
            orderItemService.priceProductInRow(sessionId);
            return "redirect:checkoutOrder";
        } else {
            orderItemService.priceProductInRowForUser(user);
            return "redirect:checkoutOrder";
        }
    }

    @GetMapping("/basket/{id}")
    public String deleteProductInBasket(@PathVariable("id") Long id) {
        orderItemService.delete(id);
        return "redirect:/basket";
    }

    @GetMapping("/checkoutOrder")
    public String checkout(@AuthenticationPrincipal User user,
                           HttpSession session,
                           Model model) {
        String sessionId = session.getId();

        model.addAttribute("finalPrice", orderItemService.priceAllOrder(sessionId));
        model.addAttribute("order", orderItemService.placedOrder(sessionId));
        model.addAttribute("customer", new Customer());
        model.addAttribute("address", new Address());
        model.addAttribute("cityList", cityService.allCity());

        model.addAttribute("orderForUser", orderItemService.placedOrderForUser(user));
        model.addAttribute("user", user);
        model.addAttribute("finalPriceUser", orderItemService.priceAllOrderUser(user));
        return "checkoutOrder";
    }

    @PostMapping("/checkoutOrder")
    public String orderComplete(@Valid Customer customer,BindingResult bindingResult,
                                @Valid Address address, BindingResult errorAddress,
                                @RequestParam("cityName") String cityName,
                                HttpSession session,
                                Model model) {
        String sessionId = session.getId();
        model.addAttribute("finalPrice", orderItemService.priceAllOrder(sessionId));
        model.addAttribute("order", orderItemService.placedOrder(sessionId));

        if (bindingResult.hasErrors() || errorAddress.hasErrors()){
            return "/checkoutOrder";
        }
        if (cityService.getCity(cityName) == null || cityName.isEmpty()){
            model.addAttribute("messagesErrorCity", "К сожалению, мы не работает в данном городе.");
            model.addAttribute("cityList", cityService.allCity());
            return "/checkoutOrder";

        }
        dataOrderService.saveOrder(orderItemService.placedOrder(sessionId), customer, cityName, address, sessionId);
        return "redirect:thanksOrder";
    }

    @GetMapping("/thanksOrder")
    public String thanksOrder() {
        return "thanksOrder";
    }


    @GetMapping("/basket/user")
    public String basketPageForUser(@AuthenticationPrincipal User user,
                                    Model model) {
        model.addAttribute("orderForUser", orderItemService.placedOrderForUser(user));
        return "basket";
    }

    @PostMapping("/checkoutOrder/user")
    public String orderCompleteForUser(@AuthenticationPrincipal User user,
                                       Model model) {

        dataOrderService.saveOrderForUser(orderItemService.placedOrderForUser(user), user);
        return "thanksOrder";
    }


}
