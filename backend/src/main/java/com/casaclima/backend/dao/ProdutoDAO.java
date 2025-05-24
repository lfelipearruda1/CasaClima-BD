package com.casaclima.backend.dao;

import com.casaclima.backend.model.Produto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoDAO {
    private final JdbcTemplate db;

    public ProdutoDAO(JdbcTemplate db) {
        this.db = db;
    }

    public List<Produto> listarTodos() {
        return db.query("SELECT * FROM Produto", new BeanPropertyRowMapper<>(Produto.class));
    }

    public List<Produto> listarAtivos() {
        return db.query("SELECT * FROM Produto WHERE ativo = true", new BeanPropertyRowMapper<>(Produto.class));
    }

    public List<Produto> listarTopDescontos() {
        String sql = "SELECT * FROM Produto WHERE ativo = true AND desconto > 0 ORDER BY desconto DESC LIMIT 3";
        return db.query(sql, new BeanPropertyRowMapper<>(Produto.class));
    }

    public boolean desativar(int codigo) {
        String sql = "UPDATE Produto SET ativo = false WHERE codigo = ?";
        return db.update(sql, codigo) > 0;
    }

    public boolean ativar(int codigo) {
        String sql = "UPDATE Produto SET ativo = true WHERE codigo = ?";
        return db.update(sql, codigo) > 0;
    }

    public Produto buscarPorCodigo(int codigo) {
        return db.queryForObject(
            "SELECT * FROM Produto WHERE codigo = ?",
            new BeanPropertyRowMapper<>(Produto.class),
            codigo
        );
    }

    public boolean inserir(Produto produto) {
        if (produto.getAtivo() == null) {
            produto.setAtivo(true); 
        }

        double precoComDesconto = produto.getPrecoOriginal() * (1 - produto.getDesconto() / 100);
        String sql = "INSERT INTO Produto (nome, descricao, capacidade, precoOriginal, preco, desconto, marca, caminho_imagem, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return db.update(sql,
            produto.getNome(),
            produto.getDescricao(),
            produto.getCapacidade(),
            produto.getPrecoOriginal(),
            precoComDesconto,           
            produto.getDesconto(),
            produto.getMarca(),
            produto.getCaminhoImagem(),
            produto.getAtivo()
        ) > 0;
    }

    public boolean atualizar(Produto produto) {
        double precoComDesconto = produto.getPrecoOriginal() * (1 - produto.getDesconto() / 100);
        String sql = "UPDATE Produto SET nome=?, descricao=?, capacidade=?, preco=?, desconto=?, marca=?, caminho_imagem=?, ativo=? WHERE codigo=?";
        return db.update(sql,
            produto.getNome(),
            produto.getDescricao(),
            produto.getCapacidade(),
            precoComDesconto,  
            produto.getDesconto(),
            produto.getMarca(),
            produto.getCaminhoImagem(),
            produto.getAtivo(),
            produto.getCodigo()
        ) > 0;
    }

    public boolean deletar(int codigo) {
        String sql = "DELETE FROM Produto WHERE codigo=?";
        try {
            int linhas = db.update(sql, codigo);
            System.out.println("DELETE SQL executado. Codigo: " + codigo + " | Linhas afetadas: " + linhas);
            return linhas > 0;
        } catch (Exception e) {
            System.out.println("ERRO AO EXCLUIR PRODUTO - Codigo: " + codigo);
            e.printStackTrace();
            return false;
        }
    }
}