package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.product.Category;
import com.vlad.tms.diploma.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> categoryAll(){
        return categoryRepository.findAll();
    }

    public Category checkCategoryName(String nameCategory){
        return categoryRepository.findByCategoryName(nameCategory);
    }

    public Category createNewCategory(String nameCategory){
        Category category = new Category();
        category.setCategoryName(nameCategory);
        categoryRepository.save(category);
        return category;
    }

    public Category searchCategory(String category){
        return categoryRepository.findByCategoryNameStartingWithIgnoreCase(category);
    }
}
