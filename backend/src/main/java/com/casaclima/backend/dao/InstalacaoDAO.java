package com.casaclima.backend.dao;

import com.casaclima.backend.model.Instalacao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class InstalacaoDAO {
    private final JdbcTemplate db;

    public InstalacaoDAO(JdbcTemplate db) {
        this.db = db;
    }

    public List<Instalacao> listar() {
        return db.query("SELECT * FROM Instalacao", new BeanPropertyRowMapper<>(Instalacao.class));
    }

    public int inserir(Instalacao i) {
        return db.update("INSERT INTO Instalacao (ID_instalacao, valor) VALUES (?, ?)",
                i.getIdInstalacao(), i.getValor());
    }

    public int atualizar(Instalacao i) {
        return db.update("UPDATE Instalacao SET valor=? WHERE ID_instalacao=?",
                i.getValor(), i.getIdInstalacao());
    }

    public int deletar(int idInstalacao) {
        return db.update("DELETE FROM Instalacao WHERE ID_instalacao=?", idInstalacao);
    }
}
