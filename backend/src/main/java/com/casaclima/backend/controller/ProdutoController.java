package com.casaclima.backend.controller;

import com.casaclima.backend.dao.ProdutoDAO;
import com.casaclima.backend.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoDAO dao;

    public ProdutoController(ProdutoDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return dao.listarTodos();
    }

    @GetMapping("/{codigo}")
    public Produto buscarPorCodigo(@PathVariable("codigo") int codigo) {
        return dao.buscarPorCodigo(codigo);
    }

    @PostMapping
    public boolean inserirProduto(@RequestBody Produto produto) {
        return dao.inserir(produto);
    }

    @PutMapping
    public boolean atualizarProduto(@RequestBody Produto produto) {
        return dao.atualizar(produto);
    }

    @DeleteMapping("/{codigo}")
    public boolean deletarProduto(@PathVariable("codigo") int codigo) {
        return dao.deletar(codigo);
    }
}