package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.product.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByCategoryId(Long id);

    List<Product> findProductByCategory (Category category);

    List<Product> findProductByType (Type type);

    List<Product> findProductByModel (Model model);

    List<Product> findProductByBrand (Brand brand);

}
