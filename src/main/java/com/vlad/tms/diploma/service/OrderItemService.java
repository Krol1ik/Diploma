package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.model.product.Product;
import com.vlad.tms.diploma.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
