package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
