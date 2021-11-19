package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.product.Product;
import com.vlad.tms.diploma.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findByCategory(Long id){
        return productRepository.findProductByCategoryId(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.getById(id);
    }
}
