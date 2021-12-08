package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.address.Address;
import com.vlad.tms.diploma.model.address.City;
import com.vlad.tms.diploma.model.entity.Customer;
import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.model.order.DataOrder;
import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.repository.DataOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DataOrderService {

    @Autowired
    private CountryService countryService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CityService cityService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private DataOrderRepository dataOrderRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MailSenderService mailSenderService;

    public void saveOrder(List<OrderItem> orderItem, Customer customer, String city, Address address, String session) {

        address.setCity(cityService.getCity(city));
        address.setCountry(countryService.addCountryBLR());
        addressService.saveAddress(address);

        customer.setAddress(address);
        customerService.addCustomer(customer);

        DataOrder dataOrder = new DataOrder();
        dataOrder.setOrderItem(orderItem);
        dataOrder.setCustomer(customer);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'Дата заказа: 'yyyy.MM.dd 'время: 'kk:mm");
        String dateTime = simpleDateFormat.format(date);
        dataOrder.setDateOrder(dateTime);
        dataOrderRepository.save(dataOrder);

        String sumOrder = "Сумма заказа: " + orderItemService.priceAllOrder(session) + " руб.\n";

        for (int i = 0; i < orderItem.size(); i++) {
            orderItem.get(i).setDataOrders(dataOrder);
            orderItem.get(i).setStatusOrder(true);
            orderItemService.saveOrder(orderItem.get(i));
        }

        String infoOrder = orderItem.get(0).getProductOrder().toString()+ " " + orderItem.get(0).getCount() + "шт";
        if (orderItem.size() > 0){
            for (int i = 1; i < orderItem.size(); i++) {
                infoOrder = infoOrder + ", " + orderItem.get(i).getProductOrder() + " " + orderItem.get(i).getCount() + "шт";
            }
        }

        String finalPrice = "Ваш заказ: " + infoOrder + ".\n";

        String addressInfo = "Адрес доставки: г." +city + ", ул." + address.getStreet() + ", д." + address.getNumberHouse() + "\n";
        String callYou = "С вами свяжутся для подтверждения заказа. \nСпасибо что выбрали нас!";

        String message = finalPrice + sumOrder + addressInfo + callYou;

        mailSenderService.send(customer.getEmail(), "Ваш заказ SportLine", message);
    }

    public void saveOrderForUser(List<OrderItem> orderItem, User user) {
        DataOrder dataOrder = new DataOrder();
        dataOrder.setOrderItem(orderItem);
        dataOrder.setUser(user);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'Дата заказа: 'yyyy.MM.dd 'время: 'kk:mm");
        String dateTime = simpleDateFormat.format(date);
        dataOrder.setDateOrder(dateTime);
        dataOrderRepository.save(dataOrder);
        for (int i = 0; i < orderItem.size(); i++) {
            orderItem.get(i).setDataOrders(dataOrder);
            orderItem.get(i).setStatusOrder(true);
            orderItemService.saveOrder(orderItem.get(i));
        }
    }

    public List<DataOrder> findOrderByUser(User user){
        return dataOrderRepository.findDataOrderByUser(user);
    }
}

