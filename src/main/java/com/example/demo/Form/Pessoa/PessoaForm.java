package com.example.demo.Form.Pessoa;

import java.time.LocalDate;
import java.util.List;
import com.example.demo.Enum.Sexo;
import com.example.demo.Model.Deficiencia;
import com.example.demo.Model.Pessoa;
import com.example.demo.Repository.DeficienciaRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class PessoaForm {

    @NotBlank(message = "Preencha o campo nome.")
    private String nome;

    @NotNull(message = "Preencha o campo data de nascimento.")
    @Past(message = "A data de nascimento de ser um data do passado.")
    private LocalDate nascimento;

    @NotNull(message = "Preencha o campo sexo.")
    private int sexo;
    
    @NotNull(message = "Preencha o campo deficiencia")
    private Deficiencia deficiencia;
    private List<Deficiencia> listDeficiencias;

    @NotNull(message = "Preencha o campo CEP.")
    @Size(min= 9, max = 9, message = "Preencha com um CEP valido.")

    
    private String cep;
    private String uf;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento;

    public Pessoa toEntity(){
        Sexo sexo = Sexo.fromCodigo(this.sexo);
        Pessoa pessoa = new Pessoa(nome, nascimento, sexo);
        pessoa.setDeficiencia(deficiencia);
        
        return pessoa;
    }

    public PessoaForm(Pessoa pessoa){
        this.nome = pessoa.getNome();
    }

    public void setDeficiencias(DeficienciaRepository repository){
    
        this.listDeficiencias = repository.findAll();
        System.out.println(this.listDeficiencias.size());

    }

}