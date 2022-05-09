package com.desafio.controller;

import com.desafio.model.Produto;
import com.desafio.respository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping
    public Iterable<Produto> recuperaProdutos(){
        return produtoRepository.findAll();
    }
}
