package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.order.DataOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataOrderRepository extends JpaRepository<DataOrder, Long> {
}
