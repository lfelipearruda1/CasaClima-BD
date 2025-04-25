package com.casaclima.backend.controller;

import com.casaclima.backend.dao.AvaliacaoDAO;
import com.casaclima.backend.model.Avaliacao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoDAO dao;

    public AvaliacaoController(AvaliacaoDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Avaliacao> listar() {
        return dao.listar();
    }

    @PostMapping
    public void cadastrar(@RequestBody Avaliacao avaliacao) {
        dao.inserir(avaliacao);
    }
}
