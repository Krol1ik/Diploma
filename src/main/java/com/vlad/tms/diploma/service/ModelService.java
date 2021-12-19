package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.product.Model;
import com.vlad.tms.diploma.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;


    public Model searchModel(String name){
        return modelRepository.findByModelNameStartingWithIgnoreCase(name);
    }

    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    public Model checkModelName(String modelName){
        return modelRepository.findByModelName(modelName);
    }

    public Model createNewModel(String modelName){
        Model model = new Model();
        model.setModelName(modelName);
        modelRepository.save(model);
        return model;
    }
}
