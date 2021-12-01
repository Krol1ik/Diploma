package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.product.Product;
import com.vlad.tms.diploma.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findByCategory(Long id){
        return productRepository.findProductByCategoryId(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.getById(id);
    }


    public void addFromAdmin (String brandName, String modelName, String typeName, String categoryName,
                              String description, Double price, int discount, String resultFilename) {
        Product product = new Product();
        product.setFilename("/static/img/" + resultFilename);
        product.setDescriptionProduct(description);
        product.setDiscount(discount);
        product.setPrice(price);

        if (brandService.checkBrandName(brandName) != null) {
            product.setBrand(brandService.checkBrandName(brandName));
        } else {
            product.setBrand(brandService.createNewBrand(brandName));
        }

        if (typeService.checkTypeName(typeName) != null) {
            product.setType(typeService.checkTypeName(typeName));
        } else if (typeName == "" || typeName == null){
            product.setType(null);
        } else {
            product.setType(typeService.createNewType(typeName));
        }

        if(categoryService.checkCategoryName(categoryName) != null){
            product.setCategory(categoryService.checkCategoryName(categoryName));
        } else {
            product.setCategory(categoryService.createNewCategory(categoryName));
        }

        if(modelService.checkModelName(modelName) != null){
            product.setModel(modelService.checkModelName(modelName));
        } else {
            product.setModel(modelService.createNewModel(modelName));
        }

        productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = productRepository.getById(id);
        orderItemService.deleteByProduct(product);
        productRepository.deleteById(id);
    }
}
