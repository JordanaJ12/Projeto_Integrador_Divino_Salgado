
gerarNumeroAleatorio();
valorTotal()

    function gerarNumeroAleatorio() {
        var valor = Math.floor(Math.random() * 9999 - 1000 + 1) + 1000;      
        numero.innerHTML = "Comanda #" + valor;

    }

function valorTotal() {


    var total = 0; // variável que retorna o total dos produtos que estão na LocalStorage.
    var i = 0; // variável que irá percorrer as posições
    var valor = 0; // variável que irá receber o preço do produto convertido em Float.


    for (i = 1; i <= 99; i++) // verifica até 99 produtos registrados na localStorage
    {
        var prod = localStorage.getItem("produto" + i + ""); // verifica se há itens nesta posição. 
        if (prod !== null)
        {
            // calcula o total dos itens
            valor = parseFloat(localStorage.getItem("valor" + i)); // valor convertido com o parseFloat()
            total = (total + valor); // arredonda para 2 casas decimais com o .toFixed(2)

        }
    }

    // exibe o total dos itens
    document.getElementById("precoPedido").innerHTML = "Total: R$ " + total.toFixed(2);
    }