package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.product.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByBrandName(String brandName);

    Brand findByBrandNameStartingWithIgnoreCase(String name);
}
