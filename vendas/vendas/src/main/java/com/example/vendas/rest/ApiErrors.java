package com.example.vendas.rest;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


public class ApiErrors { //vamos padronizar os erros e colocar em um array de strings

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) { //construtor que recebe uma lista de string contendo um array de erros
        this.errors = errors;
    }

    public ApiErrors(String mensagemErro){
        this.errors = Arrays.asList(mensagemErro); // vai transformar o array em array list com o asList
    }
}
