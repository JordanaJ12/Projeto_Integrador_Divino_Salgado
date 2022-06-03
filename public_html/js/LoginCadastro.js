/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var body = document.querySelector("body");


function Entrar() {//vai para a abar de entrar na conta na tela de loginCadastro
    body.className = "sign-in-js";
}
;

function Cadastrar() {//vai para a aba de cadastrar na tela de loginCadastro
    body.className = "sign-up-js";
}
;




function logadoNaConta() {
    // window.location.href='EscolhaPraca.html';//essa daqui deveria estar dentro da função, mas se colocar dentro da erro. não ta funfando dentro da function
    var cpfCliente = document.getElementById('cpfClienteId').value;
    var senhaCliente = document.getElementById('senhaClienteId').value;

    fetch('http://localhost:8080/api/clientes/logar', {
        method: "POST", //informamos o metodo usado para cadastrar
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({//montando requisiçao de post em json para enviar ao back end       
            cpf: cpfCliente,
            senha: senhaCliente,
        })

    }).then(response => response.json()).then(data => { //consumindo o back end
        console.log(data.id);
        if (data.id === undefined) {
            alert("erro no login");
        } else {
            localStorage.clear(); // se tentar logar outra pessoa vai apagar os campos e add o novo
            localStorage.setItem('idCliente', data.id);
            document.location.href = "PaginaInicial.html";
        }
    });
}



function CadastrarConta() {
    var nome = document.getElementById("nome").value;
    var cpf = document.getElementById("cpf").value;
    var email = document.getElementById("email").value;
    var senha = document.getElementById("senha").value;
    var confirmarSenha = document.getElementById("confirmarSenha").value;
    var tipoCliente = document.getElementById("tipoCliente").value;

    console.log(nome);
    console.log(tipoCliente);

    /* fetch('http://localhost:8080/api/clientes').then(function (respostaRequisicao){ //them faz alugma coisa e especificamos o que ele vai fazer
     console.log(respostaRequisicao);
     */

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




