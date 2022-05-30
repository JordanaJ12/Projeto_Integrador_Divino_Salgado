package com.example.vendas.rest.controller;
 //controller advicer é uma anotation coloca dentro do contexto do spring e conseguimos fazer um tratamento utilizando exceptionhandlers

import com.example.vendas.exception.PedidoNaoEncontradoException;
import com.example.vendas.exception.RegraNegocioException;
import com.example.vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class) //vai marcar o metodo abaixo pra ser o tratador de erro de exception que passamos no parametro
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){ //vai lidar com a regra de negocio exception
    String mensagemErro = ex.getMessage();
    return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex){
        return new ApiErrors(ex.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex){ //exception quando alguém tenta mandar um objeto que não é valido: nullo, vazio, e etc
    List<String> errors = ex.getBindingResult().getAllErrors() //esse objeto carrega as mensagens de validação e vê o que falhou
            .stream()
            .map(erro -> erro.getDefaultMessage() )
            .collect(Collectors.toList());
    return new ApiErrors(errors);
    }

}
