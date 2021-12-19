package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.product.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {

    Model findByModelName(String modelName);

    Model findByModelNameStartingWithIgnoreCase(String name);
}
