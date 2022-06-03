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

//then faz alugma coisa e especificamos o que ele vai fazer


/*function consultaPedido () {
 var divPedido = document.querySelector('#meuPedido')
 var retorno =  fetch('http://localhost:8080//api/pedidos')
 var pedido= retorno.json()
 console.log(pedido);
 // preencheTela(cursos)
 }
 
 consultaPedido ();
 
 */

function CadastrarConta() {
    var nome = document.getElementById("nome").value;
    var cpf = document.getElementById("cpf").value;
    var email = document.getElementById("email").value;
    var senha = document.getElementById("senha").value;
    var confirmarSenha = document.getElementById("confirmarSenha").value;
    var tipoCliente = document.getElementById("tipoCliente").value;
    console.log(nome);
    console.log(tipoCliente);
    fetch('http://localhost:8080/api/clientes', {
        method: "POST", //informamos o metodo usado para cadastrar
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({//montando requisiçao de post em json para enviar ao back end
            nome: nome,
            cpf: cpf,
            email: email,
            senha: senha,
            tipo_cliente: tipoCliente
        })

    }).then(function (respostaRequisicao) {
        console.log(respostaRequisicao);
    });
}

function finalizarCompra() {
    
    document.location.href="PedidoFinalizado.html";
    /*
    var vetorProd = [];
    var vetorValor = [];
    var vetorQuant = [];

    var total = 0; // variável que retorna o total dos produtos que estão na LocalStorage.
    var i = 0; // variável que irá percorrer as posições
    var valor = 0; // variável que irá receber o preço do produto convertido em Float.
    var qtd = 0;
    var prod;
    var prodPedido = "";
    console.log(vetorProd);
    for (i = 1; i <= 99; i++) // verifica até 99 produtos registrados na localStorage
    {
        var prod = localStorage.getItem("produto" + i); // verifica se há itens nesta posição. 
        if (prod !== null)
        {

vetorProd.push(prod);

// exibe os dados da lista dentro da div itens
            qtd += localStorage.getItem("qtd" + i) + " ";
            vetorQuant.push(qtd);
            prodPedido += prod + " ";
            valor += localStorage.getItem("valor" + i + " ");
            // calcula o total dos itens
            valor = parseFloat(localStorage.getItem("valor" + i)); // valor convertido com o parseFloat()
            vetorValor.push(valor);
            total = (total + valor); // arredonda para 2 casas decimais com o .toFixed(2)


        }

    }
    

// exibe o total dos itens
//document.getElementById("total").innerHTML = " R$ " + total.toFixed(2);

    /*
     fetch('http://localhost:8080/api/pedidos', {
     method: "POST", //informamos o metodo usado para cadastrar
     headers: {
     "Content-Type": "application/json"
     },
     body: JSON.stringify({//montando requisiçao de post em json para enviar ao back end
     
     cliente: getCPFCliente,
     total: total,
     status: 'PREPARACAO',
     itens:{
     descricaoroduto:
     precoUnitario:
     quantitade:
     }
     })
     
     }).then(function (respostaRequisicao) {
     console.log(respostaRequisicao);
     });
     // exibe o total dos itens
     console.log(prodPedido);
     alert(total);
     //alert(prod);
     alert(valor);
     alert(qtd);
     */
}