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

    @GetMapping("/{codigo}")
    public Produto buscarPorCodigo(@PathVariable int codigo) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.buscarPorCodigo(codigo);
    }

    @PostMapping
    public boolean inserirProduto(@RequestBody Produto produto) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.inserir(produto);
    }

    @PutMapping("/{codigo}")
    public boolean atualizarProduto(@PathVariable int codigo, @RequestBody Produto produto) {
        ProdutoDAO dao = new ProdutoDAO();
        produto.setCodigo(codigo); 
        return dao.atualizar(produto);
    }

    @DeleteMapping("/{codigo}")
    public boolean deletarProduto(@PathVariable int codigo) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.deletar(codigo);
    }
}