package com.example.demo.Model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(
        name = "acesso"
)
public class Acesso {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String ip;
    @Column(
            nullable = false
    )
    private Date data_hora;

    public Acesso() {
    }
    @ManyToOne
    @JoinColumn(
            name = "usuario_id"
    )
    private Usuario usuario;
    public Long getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }

    public Date getData_hora() {
        return this.data_hora;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    public void setData_hora(final Date data_hora) {
        this.data_hora = data_hora;
    }

   }