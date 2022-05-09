package com.desafio.services;

import com.desafio.model.Pedido;
import com.desafio.model.Produto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoService {

    /**
     * Valida as seguintes informações do pedido:<br/>
     *  1. O pedido deve ter pelo menos 1 produto adicionado.<br/>
     *  2. O pedido deve ter no maximo 3 produtos adicionados.<br/>
     *
     * @param pedido
     * @throws Exception
     */
    public void validaPedido(Pedido pedido) throws Exception {
        if(pedido.getProdutos() != null) {
            if (pedido.getProdutos().isEmpty()) {
                throw new Exception("O pedido deve ter no minimo um item.");
            }
            if(pedido.getProdutos().size() > 3){
                throw new Exception("O pedido excedeu o limite de 3 itens.");
            }
        }
    }

    /**
     * Calcula o valor total do pedido:<br/>
     *  1. Percorre todos os produtos do pedido, somando o valor total de todos os produtos.<br/>
     *  2. Do valor total calculado do pedido é pego 10%.<br/>
     *  3. Por fim, é somado o valor total do pedido + 10% do valor total.<br/>
     * @param pedido
     */
    public void calculaValorTotal(Pedido pedido){
        BigDecimal valorTotal = new BigDecimal("0.0");
        BigDecimal valorJuros = new BigDecimal("0.0");
        BigDecimal valorProdutos = new BigDecimal("0.0");

        for (Produto produto : pedido.getProdutos()) {
            valorProdutos = valorProdutos.add(produto.getPreco());
        }

        valorJuros = valorProdutos.multiply(new BigDecimal("0.1"));
        valorTotal = valorProdutos.add(valorJuros);

        pedido.setValorTotal(valorTotal);
    }
}
