package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.model.order.DataOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataOrderRepository extends JpaRepository<DataOrder, Long> {

    public List<DataOrder> findDataOrderByUser(User user);
}
