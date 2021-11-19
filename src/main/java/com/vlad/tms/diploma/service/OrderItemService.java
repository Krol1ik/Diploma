package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.model.product.Product;
import com.vlad.tms.diploma.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductService productService;


    public List<OrderItem> placedOrder(){
        return orderItemRepository.findOrderItemByStatusOrder(false);
    }

    public OrderItem orderById(Long id){
        return orderItemRepository.findOrderItemById(id);
    }

    public void saveOrder(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }

    public void addOrder(Long id) {
        OrderItem orderItem = new OrderItem();
        Product product = productService.findById(id);
        orderItem.setProductOrder(product);
        orderItemRepository.save(orderItem);
    }

    public void priceProductInRow(){
        List<OrderItem> orderSum = placedOrder();

        for (int i = 0; i < orderSum.size(); i++) {
            orderSum.get(i).setPriceOrder(orderSum.get(i).getProductOrder().getPrice() * orderSum.get(i).getCount());
            orderItemRepository.save(orderSum.get(i));
        }
    }

    public double priceAllOrder(){
        double sumPrice = 0;
        List<OrderItem> sumOrder = placedOrder();
        for (int i = 0; i < sumOrder.size(); i++) {
            sumPrice = sumPrice + sumOrder.get(i).getPriceOrder();
        }
        return sumPrice;
    }
}
