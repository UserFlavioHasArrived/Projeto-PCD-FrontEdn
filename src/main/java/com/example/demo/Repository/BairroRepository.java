package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long>{

}
