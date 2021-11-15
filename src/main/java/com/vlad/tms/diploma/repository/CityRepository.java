package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.address.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
