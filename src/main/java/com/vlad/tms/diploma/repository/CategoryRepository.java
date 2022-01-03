package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName (String categoryName);

}
