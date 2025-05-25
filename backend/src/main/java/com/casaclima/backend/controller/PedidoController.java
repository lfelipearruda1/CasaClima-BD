package com.casaclima.backend.controller;

import com.casaclima.backend.dao.PedidoDAO;
import com.casaclima.backend.dto.PedidoDetalhadoDTO;
import com.casaclima.backend.dto.VendaMensalDTO;
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

    @GetMapping("/detalhados")
    public ResponseEntity<List<PedidoDetalhadoDTO>> listarPedidosDetalhados() {
        try {
            List<PedidoDetalhadoDTO> pedidosDetalhados = dao.listarPedidosDetalhados();
            return ResponseEntity.ok(pedidosDetalhados);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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

    @GetMapping("/dashboard/total")
    public ResponseEntity<Double> getTotalVendas() {
        try {
            double totalVendas = dao.findTotalVendas();
            return ResponseEntity.ok(totalVendas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/dashboard/tendencia")
    public ResponseEntity<List<VendaMensalDTO>> getVendasMensais() {
        try {
            List<VendaMensalDTO> vendasMensais = dao.findVendasMensais();
            return ResponseEntity.ok(vendasMensais);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}