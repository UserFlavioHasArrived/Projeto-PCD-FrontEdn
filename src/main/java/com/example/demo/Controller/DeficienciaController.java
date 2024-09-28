package com.example.demo.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Form.DeficienciaForm;
import com.example.demo.Model.Categoria;
import com.example.demo.Model.Deficiencia;
import com.example.demo.Repository.CategoriaRepository;
import com.example.demo.Repository.DeficienciaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/deficiencia")
public class DeficienciaController {

    @Autowired
    private DeficienciaRepository deficienciaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Deficiencia> deficiencias = deficienciaRepository.findAll();
        model.addAttribute("deficiencias", deficiencias);
        return "deficiencia/listar";
    }

    @GetMapping("/criar")
    public String criar(Model model) {
        DeficienciaForm deficienciaForm = new DeficienciaForm();

        List<Categoria> listaCategorias = categoriaRepository.findAll();
        deficienciaForm.setListCategorias(listaCategorias);
        model.addAttribute("deficiencia", deficienciaForm);
        return "deficiencia/create";
    }

    @PostMapping("/criar")
    public String salvar(@Valid Deficiencia deficiencia, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "deficiencia/criar";
        }

        deficienciaRepository.save(deficiencia);
        redirectAttributes.addFlashAttribute("successMessage", "Deficiência salva com sucesso!");
        return "redirect:/deficiencias";
    }
    
}