package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.entity.Customer;
import com.vlad.tms.diploma.model.order.DataOrder;
import com.vlad.tms.diploma.repository.DataOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataOrderService {

    @Autowired
    private DataOrderRepository dataOrderRepository;

    public DataOrder addDataOrder (Customer customer){
        DataOrder dataOrder = new DataOrder();
        dataOrder.setCustomer(customer);
        dataOrderRepository.save(dataOrder);
        return dataOrder;
    }
}
