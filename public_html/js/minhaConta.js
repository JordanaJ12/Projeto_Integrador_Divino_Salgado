"use strict";
jQuery(document).ready(function ($) {

//for Preloader

    $(window).load(function () {
        $("#loading").fadeOut(500);
    });
// scroll Up

    $(window).scroll(function () {
        if ($(this).scrollTop() > 600) {
            $('.scrollup').fadeIn('slow');
        } else {
            $('.scrollup').fadeOut('slow');
        }
    });
    $('.scrollup').click(function () {
        $("html, body").animate({scrollTop: 0}, 1000);
        return false;
    });
    //End
});


var getCPFCliente = localStorage.getItem("idCliente");
console.log(getCPFCliente)
if (getCPFCliente === null) {
    window.location.href = 'LoginCadastro.html';
}


function logoff() {

    localStorage.clear();
    document.location.href = "PaginaInicial.html"

}


//quando não especificamos o metodo esse será o GET
fetch('http://localhost:8080/api/clientes/' + getCPFCliente).//função que traz na minha conta os dados do cliente logado
        then(response => response.json()).then(data => { //consumindo o back end
    console.log(data.nome)

    var nomeResponse = data.nome;
    var cpfResponse = data.cpf;
    var emailResponse = data.email;
    var senhaResponse = data.senha;
    var confirmarSenhaResponse = data.senha;
    var tipoClienteResponse = data.tipo_cliente;
    document.getElementById("nome").value = nomeResponse; //atribuindo valor a um elemento
    document.getElementById("cpf").value = cpfResponse;
    document.getElementById("email").value = emailResponse;
    document.getElementById("senha").value = senhaResponse;
    document.getElementById("confirmarSenha").value = confirmarSenhaResponse;
    document.getElementById("tipoCliente").value = tipoClienteResponse; // ponto de atenção tenho que verificar 


});
//salvar id do cliente apos login no session storage
//verificar nas páginas que usam o id, se tem alguém logado
//para verificar irá no session storage e verifica se tem algum id

function atualizarConta() {
    var nome = document.getElementById("nome").value;
    var cpf = document.getElementById("cpf").value;
    var email = document.getElementById("email").value;
    var senha = document.getElementById("senha").value;
    var confirmarSenha = document.getElementById("confirmarSenha").value;
    var tipoCliente = document.getElementById("tipoCliente").value;


    fetch('http://localhost:8080/api/clientes/' + getCPFCliente, {
        method: "PUT", //informamos o metodo usado para cadastrar
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({//montando requisiçao de post em json para enviar ao back end
            nome: nome,
            cpf: cpf,
            email: email,
            senha: senha,
            senha: ConfirmaSenha,
            tipo_cliente: tipoCliente
        })

    }).then(function (respostaRequisicao) {
        console.log(respostaRequisicao);
    });
}


