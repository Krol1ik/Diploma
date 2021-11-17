package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    public List<OrderItem> findOrderItemByStatusOrder(boolean status);

    public OrderItem findOrderItemById(Long id);
}
