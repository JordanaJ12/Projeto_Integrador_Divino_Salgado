package com.example.vendas.rest.controller;

import com.example.vendas.domain.entity.Cliente;
import com.example.vendas.domain.entity.ClienteLogin;
import com.example.vendas.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8383")

@RestController //é a camada de controle que irá se comunicar com os clientes
@RequestMapping("/api/clientes")

public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;

    }

    @GetMapping("/{id}")
    //quando o cliente fizer um GET passando um id, ele vai pegar o id que foi passado via url dentro do argumento parametro path variable integer id
    public Cliente getClienteById(@PathVariable Integer id) {
        return clientes
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));// é uma classe onde retornamos mensagens de erros

    }


    @PostMapping //definimos a rota para chegar até aqui
    @ResponseStatus(HttpStatus.CREATED) //retorna código de status quando criado no servidor
    public Cliente save(@RequestBody @Valid Cliente cliente) throws InterruptedException { // precisamos dos dados dos clientes para salvar, e para isso o Request body é o que vamos receber em json
        Thread.sleep(1000);//pega a excecução do projeto e para ela pelo tempo determinado
        return clientes.save(cliente);
    }


    @PostMapping("/logar")//definimos a rota para chegar até aqui
    @ResponseStatus(HttpStatus.CREATED) //retorna código de status quando criado no servidor
    public Cliente logar(@RequestBody ClienteLogin cliente) throws InterruptedException { // precisamos dos dados dos clientes para salvar, e para isso o Request body é o que vamos receber em json
        Cliente encontrado = clientes.encontrarPorCpfSenha(cliente.getCpf(), cliente.getSenha());
        if(encontrado ==  null){
            return null;
        }else {
            Thread.sleep(1000);//pega a excecução do projeto e para ela pelo tempo determinado
            return encontrado;
        }
    }

    @DeleteMapping("{id}") //passamos por parametro o id para excluir
    @ResponseStatus(HttpStatus.NO_CONTENT) // codigo de status no postman retornará como 200 created
    public void delete(@PathVariable Integer id) { //passamos id como parametro
        clientes.findById(id)
                .map(cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })  //se encontrar cliente excluir ele
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));

    }

    @PutMapping("{id}") //usado para atualizar integralmente um recurso no servidor
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody @Valid Cliente cliente) {
        clientes
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));

    }

    @GetMapping
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase() //ignorar quando pesquisar com caixa alta ou baixa
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);
    }

}
