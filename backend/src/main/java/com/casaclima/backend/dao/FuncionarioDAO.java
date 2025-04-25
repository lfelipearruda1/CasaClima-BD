package com.casaclima.backend.dao;

import com.casaclima.backend.model.Funcionario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class FuncionarioDAO {
    private final JdbcTemplate db;

    public FuncionarioDAO(JdbcTemplate db) {
        this.db = db;
    }

    public List<Funcionario> listar() {
        return db.query("SELECT * FROM Funcionario", new BeanPropertyRowMapper<>(Funcionario.class));
    }
}