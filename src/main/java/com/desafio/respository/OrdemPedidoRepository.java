package com.desafio.respository;

import com.desafio.model.OrdemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemPedidoRepository extends JpaRepository<OrdemPedido, Long> {
}
