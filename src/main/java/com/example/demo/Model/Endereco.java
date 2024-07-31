package com.example.demo.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(
        name = "endereco"
)
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String logradouro;
    @Column(
            nullable = false
    )
    private String cep;
    @Column(
            nullable = true
    )
    private int numero;

    @ManyToOne
    private Bairro bairro;
 ;

    public Endereco() {
    }

    public Long getId() {
        return this.id;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public String getCep() {
        return this.cep;
    }

    public int getNumero() {
        return this.numero;
    }



    public void setId(final Long id) {
        this.id = id;
    }

    public void setLogradouro(final String logradouro) {
        this.logradouro = logradouro;
    }

    public void setCep(final String cep) {
        this.cep = cep;
    }

    public void setNumero(final int numero) {
        this.numero = numero;
    }



}