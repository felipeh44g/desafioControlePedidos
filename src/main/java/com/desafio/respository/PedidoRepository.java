package com.desafio.respository;

import com.desafio.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p")
    List<Pedido> findAllPedidos();

    @Query("SELECT p FROM Pedido p WHERE p.ordemPedido IS NULL")
    List<Pedido> findAllPedidosSemOrdemPedido();

}
