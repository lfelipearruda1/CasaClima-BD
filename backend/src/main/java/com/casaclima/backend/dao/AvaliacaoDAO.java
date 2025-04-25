package com.casaclima.backend.dao;

import com.casaclima.backend.model.Avaliacao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvaliacaoDAO {
    private final JdbcTemplate db;

    public AvaliacaoDAO(JdbcTemplate db) {
        this.db = db;
    }

    public List<Avaliacao> listar() {
        String sql = "SELECT * FROM Avaliacao";
        return db.query(sql, new BeanPropertyRowMapper<>(Avaliacao.class));
    }

    public void inserir(Avaliacao avaliacao) {
        String sql = "INSERT INTO Avaliacao (nota, descricao, data, fk_Cliente_cod_cliente, fk_Produto_codigo) VALUES (?, ?, ?, ?, ?)";
        db.update(sql,
            avaliacao.getNota(),
            avaliacao.getDescricao(),
            avaliacao.getData(),
            avaliacao.getFkClienteCodCliente(),
            avaliacao.getFkProdutoCodigo()
        );
    }
}
