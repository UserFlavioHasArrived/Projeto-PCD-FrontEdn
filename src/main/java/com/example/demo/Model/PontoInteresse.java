package com.example.demo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(
        name = "ponto_interesse"
)
public class PontoInteresse implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String tipo;
    @Column(
            nullable = true
    )
    private String contato;
    @ManyToOne
    @JoinColumn(
            name = "endereco_id"
    )
    private Endereco endereco;

    public PontoInteresse() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getContato() {
        return this.contato;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public void setContato(final String contato) {
        this.contato = contato;
    }

    public void setEndereco(final Endereco endereco) {
        this.endereco = endereco;
    }

}