package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Pessoa;
import java.util.List;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

    List<Pessoa> findByAtivo(boolean ativo);

}
