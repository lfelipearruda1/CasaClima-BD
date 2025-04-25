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

    public Produto buscarPorCodigo(int codigo) {
        return db.queryForObject(
            "SELECT * FROM Produto WHERE codigo = ?",
            new BeanPropertyRowMapper<>(Produto.class),
            codigo
        );
    }

    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO Produto (nome, descricao, capacidade, preco, marca) VALUES (?, ?, ?, ?, ?)";
        return db.update(sql,
            produto.getNome(),
            produto.getDescricao(),
            produto.getCapacidade(),
            produto.getPreco(),
            produto.getMarca()
        ) > 0;
    }

    public boolean atualizar(Produto produto) {
        String sql = "UPDATE Produto SET nome=?, descricao=?, capacidade=?, preco=?, marca=? WHERE codigo=?";
        return db.update(sql,
            produto.getNome(),
            produto.getDescricao(),
            produto.getCapacidade(),
            produto.getPreco(),
            produto.getMarca(),
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