package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    @Query("SELECT c FROM Cidade c WHERE c.nome = :cidadeNome")
Cidade findCidadeByNome(@Param("cidadeNome")String nome);

}

