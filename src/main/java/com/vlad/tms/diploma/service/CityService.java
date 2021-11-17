package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.address.City;
import com.vlad.tms.diploma.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> allCity(){
        return cityRepository.findAll();
    }

    public City getCity(String cityName){
        return cityRepository.findByCityName(cityName);
    }
}
