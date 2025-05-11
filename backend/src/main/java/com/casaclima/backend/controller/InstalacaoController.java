package com.casaclima.backend.controller;

import com.casaclima.backend.dao.InstalacaoDAO;
import com.casaclima.backend.model.Instalacao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instalacoes")
public class InstalacaoController {
    private final InstalacaoDAO dao;

    public InstalacaoController(InstalacaoDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Instalacao> listar() {
        return dao.listar();
    }

    @PostMapping
    public void inserir(@RequestBody Instalacao instalacao) {
        dao.inserir(instalacao);
    }

    @PutMapping
    public void atualizar(@RequestBody Instalacao instalacao) {
        dao.atualizar(instalacao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") int id) {
        dao.deletar(id);
    }

}
