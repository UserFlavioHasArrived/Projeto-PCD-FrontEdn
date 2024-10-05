package com.example.demo.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Form.DeficienciaForm;
import com.example.demo.Form.Pessoa.PessoaForm;
import com.example.demo.Model.Categoria;
import com.example.demo.Model.Deficiencia;
import com.example.demo.Model.Pessoa;
import com.example.demo.Repository.CategoriaRepository;
import com.example.demo.Repository.DeficienciaRepository;
import com.example.demo.Service.DeficienciaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/deficiencia")
public class DeficienciaController {

    @Autowired
    private DeficienciaRepository deficienciaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired 
    private DeficienciaService deficienciaService;

    @GetMapping("/listar")
    public String index(Model model, @RequestParam("display") Optional<String> display){
        String finalDisplay = display.orElse("true");

        List<Deficiencia> deficiencias = deficienciaRepository.findByAtivo(Boolean.valueOf(finalDisplay));
        model.addAttribute("deficiencias", deficiencias);
        return "deficiencia/listar";
    }

    @GetMapping("/criar")
    public String criar(Model model) {
        DeficienciaForm deficienciaForm = new DeficienciaForm();

        List<Categoria> listaCategorias = categoriaRepository.findAll();
        deficienciaForm.setListCategorias(listaCategorias);
        model.addAttribute("deficiencia", deficienciaForm);
        return "deficiencia/criar";
    }

    @PostMapping("/criar")
    public String salvar(@Valid DeficienciaForm deficienciaForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "deficiencia/criar";
        }

        deficienciaService.create(deficienciaForm);
        redirectAttributes.addFlashAttribute("successMessage", "Deficiência salva com sucesso!");
        return "redirect:/deficiencia/listar";
    }

    @GetMapping("/editar/{id}")
    public String update(@PathVariable Long id, Model model){

        Optional<Deficiencia> deficiencia = deficienciaRepository.findById(id);

        DeficienciaForm deficienciaForm = new DeficienciaForm(deficiencia.orElseThrow());

        List<Categoria> listaCategorias = categoriaRepository.findAll();
        deficienciaForm.setListCategorias(listaCategorias);
        model.addAttribute("deficienciaForm", deficienciaForm);
        return "deficiencia/editar";
    }

    @PostMapping("/editar/{id}")
    public String update(
        @PathVariable Long id, 
        @Valid DeficienciaForm deficienciaForm, 
        BindingResult bindingResult, 
        Model model, 
        RedirectAttributes redirectAttributes
    ){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/deficiencia/editar";
        }

        deficienciaService.update(deficienciaForm, id);
            redirectAttributes.addFlashAttribute("successMessage", "Alterado com sucesso!");
             return "redirect:/deficiencia/listar";
    }
    @GetMapping("/visualizar/{id}")
    public String visualizar(@PathVariable Long id, Model model) {

        Optional<Deficiencia> deficiencia = deficienciaRepository.findById(id);
        DeficienciaForm deficienciaForm = new DeficienciaForm(deficiencia.get());
    
        List<Categoria> listaCategoria = categoriaRepository.findAll();
        deficienciaForm.setListCategorias(listaCategoria);


        model.addAttribute("deficienciaForm", deficienciaForm);
        model.addAttribute("id", deficiencia.get().getId());

        return "/deficiencia/listar";
    }

    @GetMapping("/deletar/{id}")
    public String remover(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Deficiencia> deficiencia = this.deficienciaRepository.findById(id);
        Deficiencia deficienciaModel = deficiencia.get();

        if (deficienciaModel.isAtivo()) {
            deficienciaModel.setAtivo(false);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Excluído com sucesso!");
        } else {
            deficienciaModel.setAtivo(true);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Recuperado com sucesso!");
        }

        this.deficienciaRepository.save(deficienciaModel);

        return "redirect:/deficiencia/listar";
    }
}
