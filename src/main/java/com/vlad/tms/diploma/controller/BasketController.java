package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.address.Address;
import com.vlad.tms.diploma.model.address.City;
import com.vlad.tms.diploma.model.address.Country;
import com.vlad.tms.diploma.model.entity.Customer;
import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.service.CityService;
import com.vlad.tms.diploma.service.CountryService;
import com.vlad.tms.diploma.service.DataOrderService;
import com.vlad.tms.diploma.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class BasketController {
    @Autowired
    private CountryService countryService;
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
    public String placedOrder(@RequestParam("orderId") List<OrderItem> orderItem,
                              @RequestParam("orderCount") List<Integer> count,
                              Model model) {

        for (int i = 0; i < orderItem.size(); i++) {
            orderItem.get(i).setCount(count.get(i));

        }
        return "redirect:checkoutOrder";
    }

    @GetMapping("/checkoutOrder")
    public String checkout(Model model){
        model.addAttribute("customer", new Customer());
        model.addAttribute("address", new Address());
        model.addAttribute("cityList", cityService.allCity());
        return "checkoutOrder";
    }

    @PostMapping("/checkoutOrder")
    public String orderComplete(Customer customer,
                                Address address,
                                @RequestParam ("cityName") String cityName,
                                Model model){

        dataOrderService.saveOrder(orderItemService.placedOrder(), customer, cityName, address);
        return "redirect:thanksOrder";
    }

    @GetMapping("/thanksOrder")
    public String thanksOrder(){
        return "thanksOrder";
    }
}
