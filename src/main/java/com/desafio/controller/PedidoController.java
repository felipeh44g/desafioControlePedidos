package com.desafio.controller;

import com.desafio.model.Pedido;
import com.desafio.rabbitmq.sender.PedidoSender;
import com.desafio.respository.PedidoRepository;
import com.desafio.services.PedidoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    public RabbitTemplate amqpPedido;

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public List<Pedido> recuperarPedidos(){
        return pedidoRepository.findAllPedidos();
    }

    @PostMapping
    public Pedido cadastraPedido(@RequestBody Pedido pedido) throws Exception {
        pedidoService.validaPedido(pedido);
        pedidoService.calculaValorTotal(pedido);
        Pedido novoPedido = pedidoRepository.save(pedido);
        amqpPedido.convertAndSend(PedidoSender.EXCHANGE, PedidoSender.ROUTING_KEY, pedido);
        return novoPedido;
    }

}
