package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findOrderItemByStatusOrderAndUserAndCookie(boolean status, User user, String cookie);

    List<OrderItem> findOrderItemByUserAndStatusOrder(User user, boolean status);

    void deleteByProductOrder(Product product);
}
