package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.address.Address;
import com.vlad.tms.diploma.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByUserUsername(String name);
}
