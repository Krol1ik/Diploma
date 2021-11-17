package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.order.DataOrder;
import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.repository.DataOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataOrderService {

    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private DataOrderRepository dataOrderRepository;

    public void saveOrder(List<OrderItem> orderItem){
        DataOrder dataOrder = new DataOrder();
        dataOrder.setOrderItem(orderItem);
        dataOrderRepository.save(dataOrder);
        for (int i = 0; i < orderItem.size(); i++) {
            orderItem.get(i).setDataOrders(dataOrder);
            orderItemService.saveOrder(orderItem.get(i));
        }
    }
}
