package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.order.DataOrder;
import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.model.product.Product;
import com.vlad.tms.diploma.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem addOrderItem(DataOrder dataOrder, Product product){
        OrderItem orderItem = new OrderItem();
        orderItem.setDataOrders(dataOrder);
        orderItem.setProductOrder(product);
        orderItemRepository.save(orderItem);
        return orderItem;
    }

    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
