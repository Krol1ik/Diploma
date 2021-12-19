package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.product.Brand;
import com.vlad.tms.diploma.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand searchBrand (String name){
        return brandRepository.findByBrandNameStartingWithIgnoreCase(name);
    }

    public Brand checkBrandName(String brandName){
        return brandRepository.findByBrandName(brandName);
    }

    public Brand createNewBrand(String brandName){
        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brandRepository.save(brand);
        return brand;
    }
}
