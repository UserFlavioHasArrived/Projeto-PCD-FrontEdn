package com.example.demo.Form;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.Model.Categoria;
import com.example.demo.Model.Deficiencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class DeficienciaForm {
    
    @NotBlank(message = "Preencha o campo nome.")
    private String nome;

    @NotNull(message = "Preencha o campo categoria")
    private Categoria categoria;
    private List<Categoria> listCategorias;


}
