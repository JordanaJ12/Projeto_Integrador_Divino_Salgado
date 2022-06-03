package com.example.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //o jpa entende que todas as propriedades são colunas na base de dados
@Table(name = "cliente") //estamos dizendo para o jpa que essa seria uma tabela cliente
public class Cliente {

    @Id //define qual a primmary key da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) //representa o auto incremento, O IDENTIFY quer dizer que o mysql que vai criar
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100) //não precisa colocar o @column pois o entity já faz isso
    @NotEmpty(message = "{campo.nome.obrigatorio}") //na hora de fazer a validação de uma instancia de cliente e verificar se a propriedade de baixo está vazia, nula ou string vazia sem digito vai lançar um erro de validação
    private String nome;

    @Column( name = "cpf", length = 11)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column( name = "email", length = 100)
    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;

    @Column( name = "senha", length = 100)
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    @Column( name = "tipo_cliente", length = 15)
    @NotEmpty(message = "{campo.tipo_cliente.obrigatorio}")
    private String tipo_cliente;


    @JsonIgnore
    //ela vai dizer para o transformador de objetos em Json que deve ignorar totalmente essa propriedade que no caso é ignorar os pedidos, vai mostrar apenas id e nome
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    // um cliente para muitos pedidos //mapedby essa classe no banco de dados não tem chave para pedidos e quem tem é a tabela de pedidos mas queremos fazer um join partindo da tabela de cliente e trazendo os pedidos
    private Set<Pedido> pedidos;


    public Cliente(Integer id, String nome, String cpf, String email, String senha, String tipo_cliente){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.tipo_cliente = tipo_cliente;
    }
}

