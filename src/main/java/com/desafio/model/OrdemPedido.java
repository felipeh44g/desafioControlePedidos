package com.desafio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ordem_pedido")
@Getter
@Setter
public class OrdemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ordemPedido")
    private List<Pedido> pedidos;

}
