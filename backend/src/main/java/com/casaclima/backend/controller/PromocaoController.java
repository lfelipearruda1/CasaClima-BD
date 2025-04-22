package com.casaclima.backend.controller;

import com.casaclima.backend.dao.PromocaoDAO;
import com.casaclima.backend.model.Promocao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promocoes")
public class PromocaoController {

    private final PromocaoDAO dao;

    public PromocaoController(PromocaoDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Promocao> listar() {
        return dao.listar();
    }

    @PostMapping
    public void cadastrar(@RequestBody Promocao promocao) {
        dao.inserir(promocao);
    }
}
