package com.casaclima.backend.controller;

import com.casaclima.backend.model.Carrinho;
import com.casaclima.backend.model.ItemDoCarrinho;
import com.casaclima.backend.dao.CarrinhoDAO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    private final CarrinhoDAO dao;

    public CarrinhoController(CarrinhoDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/{codCliente}")
    public Carrinho getCarrinho(@PathVariable int codCliente) {
        return dao.getCarrinho(codCliente);
    }

    @PostMapping("/{codCliente}")
    public void adicionarItem(@PathVariable int codCliente, @RequestBody ItemDoCarrinho item) {
        dao.adicionarItem(codCliente, item);
    }

    @DeleteMapping("/{codCliente}")
    public void removerItem(@PathVariable int codCliente, @RequestBody ItemDoCarrinho item) {
        dao.removerItem(codCliente, item);
    }
}