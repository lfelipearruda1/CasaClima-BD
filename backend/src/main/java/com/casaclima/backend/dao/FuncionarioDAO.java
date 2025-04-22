package com.casaclima.backend.dao;

import com.casaclima.backend.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FuncionarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Funcionario> listar() {
        String sql = "SELECT * FROM Funcionario";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Funcionario.class));
    }

    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionario (matricula, nome, rua, numero, cidade, bairro, data_nascimento, telefone, supervisor) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            funcionario.getMatricula(),
            funcionario.getNome(),
            funcionario.getRua(),
            funcionario.getNumero(),
            funcionario.getCidade(),
            funcionario.getBairro(),
            funcionario.getDataNascimento(),
            funcionario.getTelefone(),
            funcionario.getSupervisor()
        );
    }
}
