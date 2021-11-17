package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    public Country findById(int id);

    Country findByCountryName(String countryName);
}
