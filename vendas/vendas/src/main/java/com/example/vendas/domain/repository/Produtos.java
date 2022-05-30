package com.example.vendas.domain.repository;

import com.example.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> { //como na tabela produto existe uma chave do tipo integer colocamos aqui


}
