package com.casaclima.backend.controller;

import com.casaclima.backend.dao.FuncionarioDAO;
import com.casaclima.backend.model.Funcionario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioDAO dao;

    public FuncionarioController(FuncionarioDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Funcionario> listar() {
        return dao.listar();
    }
}