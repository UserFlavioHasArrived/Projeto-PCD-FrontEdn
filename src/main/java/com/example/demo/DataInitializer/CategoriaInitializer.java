package com.example.demo.DataInitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Model.Categoria;
import com.example.demo.Repository.CategoriaRepository;


@Component
public class CategoriaInitializer {

    @DataInitializerType(repository = CategoriaRepository.class, model = Categoria.class)
    private String[] categorias = {
        "Físicas", 
        "Intelectuais", 
        "Sensoriais",
        "Psicossociais ou Mentais",
        "Neurológicas", 
        "Múltiplas",
        "Aprendizagem"
    };
}