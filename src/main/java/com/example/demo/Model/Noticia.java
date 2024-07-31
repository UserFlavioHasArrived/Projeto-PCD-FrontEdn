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
        name = "noticia"
)
public class Noticia implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String descricao;
    @Column(
            nullable = false
    )
    private String imagem;
    @ManyToOne
    @JoinColumn(
            name = "usuario_id"
    )
    private Usuario usuario;

    public Noticia() {
    }

    public Long getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getImagem() {
        return this.imagem;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public void setImagem(final String imagem) {
        this.imagem = imagem;
    }

    public void setUsuario(final Usuario usuario) {
        this.usuario = usuario;
    }

}