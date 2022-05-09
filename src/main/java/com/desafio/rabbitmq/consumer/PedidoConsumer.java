package com.desafio.rabbitmq.consumer;

import com.desafio.model.Pedido;
import com.desafio.rabbitmq.sender.PedidoSender;
import com.desafio.services.RelatorioService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PedidoConsumer {

    @Autowired
    RelatorioService relatorioService;

    @RabbitListener(queues = PedidoSender.QUEUE)
    public void recebePedidoQueue(Pedido pedido) throws IOException {
        relatorioService.adicionaPedidoRelatorio(pedido);
    }

}
