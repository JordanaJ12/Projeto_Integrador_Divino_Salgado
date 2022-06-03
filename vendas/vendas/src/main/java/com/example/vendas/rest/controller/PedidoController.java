package com.example.vendas.rest.controller;

import com.example.vendas.domain.entity.ItemPedido;
import com.example.vendas.domain.entity.Pedido;
import com.example.vendas.domain.enums.StatusPedido;
import com.example.vendas.rest.dto.AtualizacaoStatusPedidoDTO;
import com.example.vendas.rest.dto.InformacaoItemPedidoDTO;
import com.example.vendas.rest.dto.InformacoesPedidoDTO;
import com.example.vendas.rest.dto.PedidoDTO;
import com.example.vendas.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "http://localhost:8383")

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service; //CAMADA DE SERVIÇO, que é a de negocio

    public PedidoController(PedidoService service) {

        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto) {  //retornamos o id do pedido gerado, com o request body iremos receber o json dos pedidos
        Pedido pedido = service.salvar(dto);// vamos transformar o dto em um modelo de dados para enviar para o nosso serviço, pois o serviço processa o pagamento
        return pedido.getId(); //retornamos o codigo do pedido

    }


    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {

        return service.obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("{id}") //é usado para atualizar, neste caso será para atualizar o pedido, mas temos que passar TODAS AS INFORMAÇÕES DO PEDIDO
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){ //vamos receber um objeto que vai trazer o novo status do pedido

        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus)); //valueof vai pegar a string(novoStatus) e se for igual ao valor de REALIZADO OU CANCELADO, vai transformar o valor


    }


    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name()) // o metodo name pega o valor REALIZADO OU CANCELADO e transforma em String
                .items(converter(pedido.getItens()))
                .build();
    }

    private  List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return  itens.stream().map(
                item -> InformacaoItemPedidoDTO
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }
}
