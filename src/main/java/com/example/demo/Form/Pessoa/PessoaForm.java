package com.example.demo.Form.Pessoa;

import com.example.demo.Model.Pessoa;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaForm {

    @NotBlank(message = "Preencha o campo nome.")
    private String nome;


    public Pessoa toEntity(){
        return new Pessoa(nome);
    }

    public PessoaForm(Pessoa pessoa){
        this.nome = pessoa.getNome();
    }

}