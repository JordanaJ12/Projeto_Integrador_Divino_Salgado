package com.example.vendas.domain.repository;

import com.example.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
// dando um interface e extends estamos herdando da classe JPA todos os seus métodos que auxiliam no banco de dados
public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
//estamos dizendo que a query fará a consulta do nome na tabela cliente em sql nativo
    List<Cliente> encontrarPorNome(@Param("nome") String nome); //o spring vai trasnformar esse método em uma query do banco

    @Query(value = " select * from Cliente c where c.nome =:nome ", nativeQuery = true)
   Cliente findByNome(String nome);

    boolean existsByNome(String nome);

    @Query(value = " select c from Cliente c join left fetch c.pedidos where c.id =:id ", nativeQuery = true)
    Cliente findClienteFetchPedidos(@Param("id") Integer id); //com o id vamos buscar tanto o cliente que tem o id e junto deles queremos trazer os pedidos da base de dados


}