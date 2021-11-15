package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
