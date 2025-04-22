package com.casaclima.backend.controller;

import com.casaclima.backend.dao.TransporteDAO;
import com.casaclima.backend.model.Transporte;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transportes")
public class TransporteController {
    private final TransporteDAO dao;

    public TransporteController(TransporteDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Transporte> listar() {
        return dao.listar();
    }

    @PostMapping
    public void inserir(@RequestBody Transporte transporte) {
        dao.inserir(transporte);
    }

    @PutMapping
    public void atualizar(@RequestBody Transporte transporte) {
        dao.atualizar(transporte);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        dao.deletar(id);
    }
}
