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

    public void saveOrder(List<OrderItem> orderItem, Customer customer, String city, Address address) {

        address.setCity(cityService.getCity(city));
        address.setCountry(countryService.addCountryBLR());
        addressService.saveAddress(address);

        customer.setAddress(address);
        customerService.addCustomer(customer);

        DataOrder dataOrder = new DataOrder();
        dataOrder.setOrderItem(orderItem);
        dataOrder.setCustomer(customer);
        dataOrderRepository.save(dataOrder);
        for (int i = 0; i < orderItem.size(); i++) {
            orderItem.get(i).setDataOrders(dataOrder);
            orderItem.get(i).setStatusOrder(true);
            orderItemService.saveOrder(orderItem.get(i));
        }
    }

    public void saveOrderForUser(List<OrderItem> orderItem, User user) {
        DataOrder dataOrder = new DataOrder();
        dataOrder.setOrderItem(orderItem);
        dataOrder.setUser(user);

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

