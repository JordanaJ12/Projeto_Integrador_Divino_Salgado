package com.example.vendas.service.impl;

import com.example.vendas.domain.entity.Cliente;
import com.example.vendas.domain.entity.ItemPedido;
import com.example.vendas.domain.entity.Pedido;
import com.example.vendas.domain.entity.Produto;
import com.example.vendas.domain.enums.StatusPedido;
import com.example.vendas.domain.repository.Clientes;
import com.example.vendas.domain.repository.ItensPedido;
import com.example.vendas.domain.repository.Pedidos;
import com.example.vendas.domain.repository.Produtos;
import com.example.vendas.exception.PedidoNaoEncontradoException;
import com.example.vendas.exception.RegraNegocioException;
import com.example.vendas.rest.dto.ItemPedidoDTO;
import com.example.vendas.rest.dto.PedidoDTO;
import com.example.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // faz com que seja gerado um construtor com todos os argumentos obrigatorios, que serão os que tem final
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;                         //vamos precisar acessar a base de dados Pedidos
    private final Clientes clientesRepository;
    private final Produtos produtosRespository;
    private  final ItensPedido itemsPedidoRepository;

    @Override
    @Transactional //vai garantir que tudo aconteça com sucesso, se algo der errado ele faz um rollback. Se ele salvar o pedido e tiver erro no itens pedido ele vai desfazer o pedido
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente(); // para achar o cliente pelo id

        Cliente cliente = clientesRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException(("Código de cliente inválido."))); //obtem o cliente por id, caso contrario lançamos uma regra de negocio uma exception especifica

        //pedido já feito
        Pedido pedido = new Pedido(); //objeto do tipo pedido
        pedido.setTotal(dto.getTotal()); //populamos o total
        pedido.setDataPedido(LocalDate.now()); //data do pedido que é agora
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO ); //quando eu realizar um pedido ele automaticamente será realizado

        List<ItemPedido> itemsPedido = converterItens(pedido, dto.getItems());
        repository.save(pedido); //aqui sim estamos salvando o pedido e obtendo a referencia do id no banco
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);

    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido); //atualizamos o status
                    return repository.save(pedido); // salvamos o pedido
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) { //vai transformar o item em item de pedido
        if (itens.isEmpty()) { //verificando se a lista está vazia
        throw  new RegraNegocioException("Não é possível realizar pedido sem itens.");

        }
        return itens
                .stream() // é uma stream de dtos e tranformamos em uma stream de itens pedido
                .map(dto ->{
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRespository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido." + idProduto
                                    )); //passando qual código foi enviado que não existe o produto cadastrado no banco

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido); // de qual pedido é esse item, passando a referência do pedido que ainda não foi salvo
                    itemPedido.setProduto(produto);
                    return itemPedido;

        }).collect(Collectors.toList()); //trasnforma a outra stream em uma lista  de item pedido
    }
}
