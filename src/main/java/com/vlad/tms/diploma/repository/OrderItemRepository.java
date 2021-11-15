package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
