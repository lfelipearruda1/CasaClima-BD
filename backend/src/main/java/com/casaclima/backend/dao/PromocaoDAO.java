package com.casaclima.backend.dao;

import com.casaclima.backend.model.Promocao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PromocaoDAO {
    private final JdbcTemplate db;

    public PromocaoDAO(JdbcTemplate db) {
        this.db = db;
    }

    public List<Promocao> listar() {
        String sql = "SELECT * FROM Promocao";
        return db.query(sql, new BeanPropertyRowMapper<>(Promocao.class));
    }

    public void inserir(Promocao promocao) {
        String sql = "INSERT INTO Promocao (ID_desconto, tipo_desconto, valor) VALUES (?, ?, ?)";
        db.update(sql,
            promocao.getIdDesconto(),
            promocao.getTipoDesconto(),
            promocao.getValor()
        );
    }
}
