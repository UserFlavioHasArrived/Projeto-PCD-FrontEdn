package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "numero", nullable = true)
    private String numero;

    @Column(name = "complemento", nullable = true)
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "bairro_id")
    private Bairro bairro;
}