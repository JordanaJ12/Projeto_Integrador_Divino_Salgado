package com.example.vendas.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data // é o equivalente a usar o getter, setter, tostring, equalsandhashcode, requiredeargsconstructor
@Entity
@Table(name = "produto")
public class Produto {


    @Id //usamos para identificador da chave primaria que no nosso caso é o id segundo o meu_schema
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //não precisa mas colocamos para deixar bem definido
    private Integer id;

    @Column(name = "descricao")
    @NotEmpty(message = "{campo.descricao.obrigatorio}") // notEmpty faz sentido em string pois pode ser vazia ou nula
    private String descricao;

    @Column(name = "{campo.preco.obrigatorio}")
    @NotNull(message = "{}") //não existe preço vazio ou é 0 ou nulo
    private BigDecimal preco;

}
