package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Form.DeficienciaForm;
import com.example.demo.Model.Deficiencia;

import com.example.demo.Repository.DeficienciaRepository;



@Service
public class DeficienciaService {

    @Autowired
    private DeficienciaRepository deficienciaRepository;

    public List<Deficiencia> findAll(){
        return deficienciaRepository.findAll(); 
    }

    public Deficiencia create(DeficienciaForm deficienciaForm){
        Deficiencia deficiencia = new Deficiencia();

        deficiencia.setNome(deficienciaForm.getNome());
        deficiencia.setCategoria(deficienciaForm.getCategoria());

        this.deficienciaRepository.save(deficiencia);

        return deficiencia;
    }

    public Deficiencia update(DeficienciaForm deficienciaForm, long id){

        Deficiencia deficiencia = this.deficienciaRepository.findById(id).orElseThrow();

        deficiencia.setNome(deficienciaForm.getNome());
        deficiencia.setCategoria(deficienciaForm.getCategoria());

        this.deficienciaRepository.save(deficiencia);

        return deficiencia;
    }
}