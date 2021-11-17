package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.address.Country;
import com.vlad.tms.diploma.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Country addCountryBLR(){
        return countryRepository.findByCountryName("Беларусь");
    }
}
