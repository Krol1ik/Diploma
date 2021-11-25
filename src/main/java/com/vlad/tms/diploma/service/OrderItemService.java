package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.entity.User;
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


    public List<OrderItem> placedOrder() {
        return orderItemRepository.findOrderItemByStatusOrderAndUser(false, null);
    }


    public void saveOrder(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public void addOrder(Long id) {
        OrderItem orderItem = new OrderItem();
        Product product = productService.findById(id);
        orderItem.setProductOrder(product);
        orderItemRepository.save(orderItem);
    }

    public void priceProductInRow() {
        List<OrderItem> orderSum = placedOrder();

        for (int i = 0; i < orderSum.size(); i++) {
            orderSum.get(i).setPriceOrder(orderSum.get(i).getProductOrder().getPrice() * orderSum.get(i).getCount());
            orderItemRepository.save(orderSum.get(i));
        }
    }

    public void priceProductInRowForUser(User user) {
        List<OrderItem> orderSum = placedOrderForUser(user);

        for (int i = 0; i < orderSum.size(); i++) {
            orderSum.get(i).setPriceOrder(orderSum.get(i).getProductOrder().getPrice() * orderSum.get(i).getCount());
            orderItemRepository.save(orderSum.get(i));
        }
    }

    public double priceAllOrder() {
        double sumPrice = 0;
        List<OrderItem> sumOrder = placedOrder();
        for (int i = 0; i < sumOrder.size(); i++) {
            sumPrice = sumPrice + sumOrder.get(i).getPriceOrder();
        }
        return sumPrice;
    }

    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }

    public void addOrderForUser(Long id, User user) {
        OrderItem orderItem = new OrderItem();
        Product product = productService.findById(id);
        orderItem.setProductOrder(product);
        orderItem.setUser(user);
        orderItemRepository.save(orderItem);
    }

    public List<OrderItem> placedOrderForUser(User user) {
        return orderItemRepository.findOrderItemByUserAndStatusOrder(user, false);
    }

    public List<OrderItem> historyOrderForUser(User user) {
        return orderItemRepository.findOrderItemByUserAndStatusOrder(user, true);
    }
}
