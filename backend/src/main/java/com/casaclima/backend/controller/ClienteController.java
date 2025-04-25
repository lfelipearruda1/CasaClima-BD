package com.casaclima.backend.controller;

import com.casaclima.backend.dao.ClienteDAO;
import com.casaclima.backend.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteDAO dao;

    public ClienteController(ClienteDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Cliente> listar() {
        return dao.listar();
    }

    @PostMapping
    public void inserir(@RequestBody Cliente cliente) {
        dao.inserir(cliente);
    }

    @PutMapping
    public void atualizar(@RequestBody Cliente cliente) {
        dao.atualizar(cliente);
    }

    @DeleteMapping("/{codCliente}")
    public void deletar(@PathVariable("codCliente") int codCliente) {
        dao.deletar(codCliente);
    }
}