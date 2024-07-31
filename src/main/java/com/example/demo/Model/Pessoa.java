package com.example.demo.Model;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "pessoa")
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 100, unique = false)
    private String nome;

    @Column(name = "ativo", nullable = false)
    @ColumnDefault("true")
    private boolean ativo = true;

    public Pessoa(String nome){
        this.nome = nome;
    }
}