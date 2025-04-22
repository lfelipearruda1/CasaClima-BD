package com.casaclima.backend.controller;

import com.casaclima.backend.dao.PedidoDAO;
import com.casaclima.backend.model.Pedido;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoDAO dao;

    public PedidoController(PedidoDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Pedido> listar() {
        return dao.listar();
    }

    @PostMapping
    public void cadastrar(@RequestBody Pedido pedido) {
        dao.inserir(pedido);
    }
}
