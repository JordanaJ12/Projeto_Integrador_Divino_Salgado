package com.example.vendas.service;

import com.example.vendas.domain.entity.Pedido;
import com.example.vendas.domain.enums.StatusPedido;
import com.example.vendas.rest.dto.PedidoDTO;

import java.util.Optional;


public interface PedidoService {
    Pedido salvar(PedidoDTO dto); //vai salvar o pedido e retorna o pedido salvo na base de dados

    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
