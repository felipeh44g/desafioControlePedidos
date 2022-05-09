package com.desafio.agendamento;

import com.desafio.model.OrdemPedido;
import com.desafio.model.Pedido;
import com.desafio.respository.OrdemPedidoRepository;
import com.desafio.respository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class AgendamentoOrdemPedido {
    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60;

    private final long DEZ_MINUTOS = MINUTO * 10;

    public static Date PROXIMA_EXEC;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    OrdemPedidoRepository ordemPedidoRepository;

    @Scheduled(fixedDelay = DEZ_MINUTOS)
    public void verificaPorHora() {
        Calendar agora = Calendar.getInstance();
        agora.add(Calendar.MINUTE, 10);
        PROXIMA_EXEC = agora.getTime();
        List<Pedido> lPedido = pedidoRepository.findAllPedidosSemOrdemPedido();
        OrdemPedido ordemPedido = new OrdemPedido();
        if(lPedido != null && !lPedido.isEmpty()){
            ordemPedido = ordemPedidoRepository.save(new OrdemPedido());
        }
        for (Pedido pedido : lPedido) {
            pedido.setOrdemPedido(ordemPedido);
            pedidoRepository.save(pedido);
        }
    }

}