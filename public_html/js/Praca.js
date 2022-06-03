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

function AddCarrinho(produto, qtd, valor, posicao)
{
   
    localStorage.setItem("produto" + posicao, produto);
     localStorage.setItem("qtd" + posicao, qtd);
     valor = valor * qtd;
     localStorage.setItem("valor" + posicao, valor);
     alert("Produto adicionado ao carrinho!");
     
}


