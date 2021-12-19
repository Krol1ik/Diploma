package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.product.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByTypeName(String typeName);

    Type findByTypeNameStartingWithIgnoreCase(String name);
}
