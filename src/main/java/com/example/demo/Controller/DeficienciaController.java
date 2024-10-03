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
     @GetMapping("/pessoa/visualizar/{id}")
    public String visualizar(@PathVariable Long id, Model model){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        PessoaForm pessoaForm = new PessoaForm(pessoa.get());

        model.addAttribute("pessoaForm", pessoaForm);
        model.addAttribute("id", pessoa.get().getId());

        return "/pessoa/visualizar";
    }
     @GetMapping("/visualizar/{id}")
    public ResponseEntity<Deficiencia> visualizar(@PathVariable Long id) {
        Optional<Deficiencia> deficiencia = deficienciaService.visualizar(id);
        if (deficiencia.isPresent()) {
            return ResponseEntity.ok(deficiencia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, Model model) {
        Optional<Deficiencia> deficiencia = deficienciaService.visualizar(id);
        model.addAttribute("deficiencia", deficiencia.orElse(null));
        return "excluir"; // Nome do template HTML para excluir
    }

    @PostMapping("/excluir/{id}")
    public String excluirConfirmado(@PathVariable Long id) {
        deficienciaService.excluir(id);
        return "redirect:/deficiencia/listar"; // Redirecionar após exclusão
    }
}