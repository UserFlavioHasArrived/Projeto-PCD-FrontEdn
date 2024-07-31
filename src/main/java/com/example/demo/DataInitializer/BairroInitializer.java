package com.example.demo.DataInitializer;

import org.springframework.stereotype.Component;
import com.example.demo.Model.Bairro;
import com.example.demo.Repository.BairroRepository;

@Component
public class BairroInitializer {

    @DataInitializerType(repository = BairroRepository.class, model = Bairro.class)
    private String[] bairros = {
        "Amizade", 
        "Água Verde", 
        "Barra do Rio Cerro", 
        "Barra do Rio Molha", 
        "Barra do Rio Cerro II", 
        "Boa Vista", 
        "Boa Vista II", 
        "Braço do Ribeirão Cavalo", 
        "Centro", 
        "Chico de Paulo", 
        "Czerniewicz", 
        "Estrada Nova", 
        "Ilha da Figueira", 
        "Jaraguá 84", 
        "Jaraguá 99", 
        "Jaraguá Esquerdo", 
        "João Pessoa", 
        "Maranata", 
        "Nereu Ramos", 
        "Nova Brasília", 
        "Parque Malwee", 
        "Rau", 
        "Rio Cerro I", 
        "Rio Cerro II", 
        "Rio da Luz", 
        "Rio Molha", 
        "Santa Luzia", 
        "Santo Antônio", 
        "São Luís", 
        "São Pedro", 
        "Tifa Martins", 
        "Três Rios do Norte", 
        "Três Rios do Sul", 
        "Vieira", 
        "Vila Baependi", 
        "Vila Lalau", 
        "Vila Lenzi", 
        "Vila Nova", 
        "Vila Rau"
    };
}