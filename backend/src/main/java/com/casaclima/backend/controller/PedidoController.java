package com.casaclima.backend.controller;

import com.casaclima.backend.dao.PedidoDAO;
import com.casaclima.backend.model.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
    public ResponseEntity<String> cadastrar(@RequestBody Pedido pedido) {
        try {
            dao.inserir(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pedido cadastrado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o pedido.");
        }
    }
}