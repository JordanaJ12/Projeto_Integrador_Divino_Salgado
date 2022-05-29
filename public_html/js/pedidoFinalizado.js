
gerarNumeroAleatorio();

    function gerarNumeroAleatorio() {
        var valor = Math.floor(Math.random() * 9999 - 1000 + 1) + 1000;      
        numero.innerHTML = "Comanda #" + valor;

    }

