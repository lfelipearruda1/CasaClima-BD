package com.casaclima.backend.controller;

import com.casaclima.backend.dao.ProdutoDAO;
import com.casaclima.backend.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @GetMapping
    public List<Produto> listarProdutos() {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.listarTodos();
    }
}
