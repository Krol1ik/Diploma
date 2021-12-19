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

    public List<Product> searchCatalog(String name){
        if(categoryService.searchCategory(name) != null){
            return productRepository.findProductByCategory(categoryService.searchCategory(name));
        } else if(typeService.searchType(name) != null){
            return  productRepository.findProductByType(typeService.searchType(name));
        } else if (modelService.searchModel(name) != null){
            return productRepository.findProductByModel(modelService.searchModel(name));
        } else if (brandService.searchBrand(name) != null){
            return productRepository.findProductByBrand(brandService.searchBrand(name));
        } else {
            return null;
        }
    }

    public List<Product> findByCategory(Long id){
        List<Product> productList = productRepository.findProductByCategoryId(id);
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getStockBalance() <= 0){
                productList.remove(i);
            }
        }
        return productList;
    }

    public List<Product> productListForAdmin (){
        return productRepository.findAll();
    }

    public List<Product> findAllProduct (){
        List<Product> productList = productRepository.findAll();
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getStockBalance() <= 0){
                productList.remove(i);
            }
        }
        return productList;
    }

    public Product findById(Long id){
        return productRepository.getById(id);
    }


    public void addFromAdmin (String brandName, String modelName, String typeName, String categoryName,
                              String description, Double price, int discount, int count,Product product) {

        product.setDescriptionProduct(description);
        product.setDiscount(discount);
        product.setPrice(price);
        product.setStockBalance(count);

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

    public void save (List<Product> products){
        productRepository.saveAll(products);
    }

    public void updateProduct(Long id, int stockBalance, int discount, double price){
        Product product = findById(id);
        product.setStockBalance(stockBalance);
        product.setDiscount(discount);
        product.setPrice(price);
        productRepository.save(product);
    }
}
