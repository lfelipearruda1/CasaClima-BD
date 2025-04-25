package com.casaclima.backend.dao;

import com.casaclima.backend.model.Pedido;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoDAO {
    private final JdbcTemplate db;

    public PedidoDAO(JdbcTemplate db) {
        this.db = db;
    }

    public List<Pedido> listar() {
        String sql = "SELECT * FROM Pedido";
        return db.query(sql, new BeanPropertyRowMapper<>(Pedido.class));
    }

    public void inserir(Pedido pedido) {
        String sql = "INSERT INTO Pedido (numero, data_de_realizacao, valor_total, status, metodo_pagamento, fk_Funcionario_matricula, fk_Cliente_cod_cliente, fk_Transporte_ID_transporte, fk_Instalacao_ID_instalacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        db.update(sql,
            pedido.getNumero(),
            pedido.getDataDeRealizacao(),
            pedido.getValorTotal(),
            pedido.getStatus(),
            pedido.getMetodoPagamento(),
            pedido.getFuncionarioMatricula(),
            pedido.getClienteId(),
            pedido.getTransporteId(),
            pedido.getInstalacaoId()
        );
    }
}
