package com.casaclima.backend.dao;

import com.casaclima.backend.model.Funcionario;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return db.query("SELECT * FROM funcionario", new BeanPropertyRowMapper<>(Funcionario.class));
    }

    public Funcionario buscarPorMatricula(String matricula) {
        try {
            String sql = "SELECT * FROM funcionario WHERE matricula = ?";
            return db.queryForObject(sql, new BeanPropertyRowMapper<>(Funcionario.class), matricula);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void inserir(Funcionario func) {
        String sql = "INSERT INTO funcionario (matricula, nome, rua, numero, cidade, bairro, data_nascimento, telefone, senha, supervisor, cpf) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        db.update(sql, func.getMatricula(), func.getNome(), func.getRua(), func.getNumero(), func.getCidade(),
                func.getBairro(), func.getDataNascimento(), func.getTelefone(), func.getSenha(),
                func.getSupervisor(), func.getCpf());
    }

    public void atualizar(String matricula, Funcionario func) {
        String sql =
            "UPDATE funcionario SET " +
                "nome=?, rua=?, numero=?, cidade=?, bairro=?, data_nascimento=?, telefone=?, " +
                // só atualiza senha se enviada:
                (func.getSenha() != null && !func.getSenha().isEmpty() ? "senha=?, " : "") +
                "supervisor=?, cpf=? WHERE matricula=?";
        if (func.getSenha() != null && !func.getSenha().isEmpty()) {
            db.update(sql, func.getNome(), func.getRua(), func.getNumero(), func.getCidade(), func.getBairro(),
                func.getDataNascimento(), func.getTelefone(), func.getSenha(),
                func.getSupervisor(), func.getCpf(), matricula);
        } else {
            sql = sql.replace("senha=?, ", ""); // remove do SQL se não vai setar
            db.update(sql, func.getNome(), func.getRua(), func.getNumero(), func.getCidade(), func.getBairro(),
                func.getDataNascimento(), func.getTelefone(),
                func.getSupervisor(), func.getCpf(), matricula);
        }
    }

    public void atualizarSenha(String matricula, String novaSenha) {
        db.update("UPDATE funcionario SET senha = ? WHERE matricula = ?", novaSenha, matricula);
    }

    public void excluir(String matricula) {
        db.update("DELETE FROM funcionario WHERE matricula = ?", matricula);
    }
}