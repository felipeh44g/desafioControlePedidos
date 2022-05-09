package com.desafio.controller;

import com.desafio.agendamento.AgendamentoOrdemPedido;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/ordemPedido")
public class OrdemPedidoController {

    @GetMapping
    public Long getMinutosProxExec(){
        Date startDate = AgendamentoOrdemPedido.PROXIMA_EXEC;
        Date finalDate = Calendar.getInstance().getTime();
        long duration  = startDate.getTime() - finalDate.getTime();

        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);

        return diffInMinutes;
    }
}
