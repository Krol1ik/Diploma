package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.product.Type;
import com.vlad.tms.diploma.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;


    public Type checkTypeName(String typeName){
        return typeRepository.findByTypeName(typeName);
    }

    public Type createNewType(String typeName){
        Type type = new Type();
        type.setTypeName(typeName);
        typeRepository.save(type);
        return type;
    }

    public List<Type> typeAll() {
        return typeRepository.findAll();
    }

}
