package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long>{

    @Query("SELECT b FROM Bairro b WHERE b.nome = :nome AND b.cidade.id = :cidadeId")
    Bairro findBairroByNomeAndCidade(@Param("nome") String bairroNome, @Param("cidadeId")Long cidadeId);

}
