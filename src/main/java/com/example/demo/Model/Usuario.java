package com.example.demo.Model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "cpf", nullable = false,length = 14)
    private String cpf;
    
    @Column(name = "email", nullable = false,length = 100)
    private String email;
    
    @Column(name = "senha", nullable = false,length = 50)
    private String senha;

    @ManyToMany
    private List<NivelAcesso> nivelAcesso;

    @Column(name = "ativo",nullable = true)
    private boolean ativo;
    
}




    