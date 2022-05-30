package com.example.vendas.domain.entity;

import com.example.vendas.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne //relacionamento de um cliente para muitos pedidos
    @JoinColumn(name = "cliente_id")
    private Cliente cliente; //Tem um relacionamento com a foreign key da tabela cliente

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total", precision = 20, scale = 2) //precision quantas casas decimais eu quero depos da virgula
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido") // coloquei a propriedade que representa o pedido (relacionamento)
    private List<ItemPedido> itens;



}

