package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.address.Address;
import com.vlad.tms.diploma.model.entity.Customer;
import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.service.CityService;
import com.vlad.tms.diploma.service.DataOrderService;
import com.vlad.tms.diploma.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String basketPage(Model model) {
        model.addAttribute("order", orderItemService.placedOrder());
        return "basket";
    }

    @PostMapping("/basket")
    public String placedOrder(@AuthenticationPrincipal User user,
                              @RequestParam(value = "orderId", required = false) List<OrderItem> orderItem,
                              @RequestParam(value = "orderCount", required = false) List<Integer> count,
                              Model model) {

        if (orderItem == null) {
            model.addAttribute("messages", "У вас еще нет товаров в корзине");
            return "basket";
        }
        for (int i = 0; i < orderItem.size(); i++) {
            orderItem.get(i).setCount(count.get(i));
            orderItemService.saveOrder(orderItem.get(i));
        }
        if (user == null) {
            orderItemService.priceProductInRow();
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
                           Model model) {
        model.addAttribute("finalPrice", orderItemService.priceAllOrder());
        model.addAttribute("order", orderItemService.placedOrder());
        model.addAttribute("customer", new Customer());
        model.addAttribute("address", new Address());
        model.addAttribute("cityList", cityService.allCity());

        model.addAttribute("orderForUser", orderItemService.placedOrderForUser(user));
        model.addAttribute("user", user);
        return "checkoutOrder";
    }

    @PostMapping("/checkoutOrder")
    public String orderComplete(Customer customer,
                                Address address,
                                @RequestParam("cityName") String cityName,
                                Model model) {

        dataOrderService.saveOrder(orderItemService.placedOrder(), customer, cityName, address);
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
