package com.example.demo.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String salvar(@Valid Deficiencia deficienciaForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "deficiencia/criar";
        }

        deficienciaRepository.save(deficienciaForm);
        redirectAttributes.addFlashAttribute("successMessage", "Deficiência salva com sucesso!");
        return "redirect:/deficiencias";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        PessoaForm pessoaForm = new PessoaForm(pessoa.get());

        model.addAttribute("pessoaForm", pessoaForm);
        model.addAttribute("id", pessoa.get().getId());

        return "/pessoa/update";
    }
}