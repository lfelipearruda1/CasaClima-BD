package com.casaclima.backend.dao;

import com.casaclima.backend.model.Transporte;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TransporteDAO {
    private final JdbcTemplate db;

    public TransporteDAO(JdbcTemplate db) {
        this.db = db;
    }

    public List<Transporte> listar() {
        return db.query("SELECT * FROM Transporte", new BeanPropertyRowMapper<>(Transporte.class));
    }

    public int inserir(Transporte t) {
        return db.update("INSERT INTO Transporte (ID_transporte, prazo_de_entrega) VALUES (?, ?)",
                t.getIdTransporte(), t.getPrazoDeEntrega());
    }

    public int atualizar(Transporte t) {
        return db.update("UPDATE Transporte SET prazo_de_entrega=? WHERE ID_transporte=?",
                t.getPrazoDeEntrega(), t.getIdTransporte());
    }

    public int deletar(int idTransporte) {
        return db.update("DELETE FROM Transporte WHERE ID_transporte=?", idTransporte);
    }
}
