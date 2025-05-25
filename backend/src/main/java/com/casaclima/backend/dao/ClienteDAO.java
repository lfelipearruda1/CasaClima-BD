package com.casaclima.backend.dao;

import com.casaclima.backend.model.Cliente;
import com.casaclima.backend.dto.ClienteMesDTO;
import com.casaclima.backend.dto.TopClienteDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ClienteDAO {
    private final JdbcTemplate db;

    public ClienteDAO(JdbcTemplate db) {
        this.db = db;
    }

    public List<Cliente> listar() {
        return db.query("SELECT * FROM Cliente", new BeanPropertyRowMapper<>(Cliente.class));
    }

    public int contarTotalClientes() {
        return db.queryForObject("SELECT COUNT(*) FROM Cliente", Integer.class);
    }

    public List<ClienteMesDTO> listarNovosClientesPorMes() {
        String sql = "SELECT MONTH(data_cadastro) AS mes, COUNT(*) AS total FROM Cliente GROUP BY MONTH(data_cadastro)";
        return db.query(sql, new BeanPropertyRowMapper<>(ClienteMesDTO.class));
    }

    public List<TopClienteDTO> listarTopClientes() {
        String sql = "SELECT c.nome AS nome, SUM(p.valor_total) AS valor " +
                    "FROM Pedido p " +
                    "JOIN Cliente c ON p.fk_Cliente_cod_cliente = c.cod_cliente " +
                    "GROUP BY c.nome " +
                    "ORDER BY valor DESC LIMIT 5";
        return db.query(sql, new BeanPropertyRowMapper<>(TopClienteDTO.class));
    }

    public int inserir(Cliente c) {
        return db.update(
            "INSERT INTO Cliente (nome, rua, numero, cidade, bairro, telefone, cpf, cnpj, email, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            c.getNome(), c.getRua(), c.getNumero(), c.getCidade(), c.getBairro(), c.getTelefone(), c.getCpf(), c.getCnpj(), c.getEmail(), c.getSenha()
        );
    }

    public int atualizar(Cliente c) {
        return db.update(
            "UPDATE Cliente SET nome=?, rua=?, numero=?, cidade=?, bairro=?, telefone=?, cpf=?, cnpj=?, email=?, senha=? WHERE cod_cliente=?",
            c.getNome(), c.getRua(), c.getNumero(), c.getCidade(), c.getBairro(), c.getTelefone(), c.getCpf(), c.getCnpj(), c.getEmail(), c.getSenha(), c.getCodCliente()
        );
    }

    public int deletar(int codCliente) {
        return db.update("DELETE FROM Cliente WHERE cod_cliente=?", codCliente);
    }
}