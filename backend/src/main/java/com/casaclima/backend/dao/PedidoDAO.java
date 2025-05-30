package com.casaclima.backend.dao;

import com.casaclima.backend.model.Pedido;
import com.casaclima.backend.model.Produto;
import com.casaclima.backend.dto.VendaMensalDTO;
import com.casaclima.backend.dto.PedidoDetalhadoDTO;
import com.casaclima.backend.model.Instalacao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.sql.Connection;
import java.sql.Date;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PedidoDAO {
    private final JdbcTemplate db;

    public PedidoDAO(JdbcTemplate db) {
        this.db = db;
    }

    public List<Pedido> listar() {
        String sql = "SELECT numero, data_de_realizacao, valor_total, status, metodo_pagamento, " +
                     "fk_Cliente_cod_cliente, fk_Transporte_ID_transporte, endereco_rua, endereco_numero, " +
                     "endereco_cidade, endereco_bairro, endereco_cep FROM Pedido";
        return db.query(sql, (rs, rowNum) -> {
            Pedido pedido = new Pedido();
            pedido.setNumero(rs.getInt("numero"));
            pedido.setDataDeRealizacao(rs.getDate("data_realizacao"));
            pedido.setValorTotal(rs.getDouble("valor_total"));
            pedido.setStatus(rs.getString("status"));
            pedido.setMetodoPagamento(rs.getString("metodo_pagamento"));
            pedido.setClienteId(rs.getInt("fk_Cliente_cod_cliente"));
            pedido.setTransporteId(rs.getInt("fk_Transporte_ID_transporte"));
            pedido.setEnderecoRua(rs.getString("endereco_rua"));
            pedido.setEnderecoNumero(rs.getInt("endereco_numero"));
            pedido.setEnderecoCidade(rs.getString("endereco_cidade"));
            pedido.setEnderecoBairro(rs.getString("endereco_bairro"));
            pedido.setEnderecoCep(rs.getString("endereco_cep"));
            return pedido;
        });
    }

    public void inserir(Pedido pedido) throws SQLException {
        System.out.println("ClienteId do pedido: " + pedido.getClienteId());
        String sqlCheckCliente = "SELECT COUNT(*) FROM cliente WHERE cod_cliente = ?";
        Integer count = db.queryForObject(sqlCheckCliente, Integer.class, pedido.getClienteId());
        if (count == null || count == 0) {
            throw new SQLException("Cliente com ID " + pedido.getClienteId() + " não encontrado.");
        }

        try (Connection connection = db.getDataSource().getConnection()) {
            connection.setAutoCommit(false);

            int transporteId = inserirTransporte(connection);
            pedido.setTransporteId(transporteId);

            String sqlPedido = "INSERT INTO Pedido (data_de_realizacao, valor_total, status, metodo_pagamento, fk_Cliente_cod_cliente, fk_Transporte_ID_transporte, endereco_rua, endereco_numero, endereco_cidade, endereco_bairro, endereco_cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmtPedido = connection.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS)) {
                if (pedido.getDataDeRealizacao() != null) {
                    stmtPedido.setDate(1, new Date(pedido.getDataDeRealizacao().getTime()));
                } else {
                    stmtPedido.setDate(1, null);
                }

                stmtPedido.setDouble(2, pedido.getValorTotal());
                stmtPedido.setString(3, pedido.getStatus());
                stmtPedido.setString(4, pedido.getMetodoPagamento());
                stmtPedido.setInt(5, pedido.getClienteId());
                stmtPedido.setInt(6, pedido.getTransporteId());
                stmtPedido.setString(7, pedido.getEnderecoRua());
                stmtPedido.setInt(8, pedido.getEnderecoNumero());
                stmtPedido.setString(9, pedido.getEnderecoCidade());
                stmtPedido.setString(10, pedido.getEnderecoBairro());
                stmtPedido.setString(11, pedido.getEnderecoCep());

                stmtPedido.executeUpdate();

                try (ResultSet rs = stmtPedido.getGeneratedKeys()) {
                    if (rs.next()) {
                        pedido.setNumero(rs.getInt(1));
                    }
                }

                if (pedido.getInstalacoes() != null) {
                    inserirInstalacoes(pedido, connection);
                }
                if (pedido.getProdutos() != null) {
                    inserirProdutos(pedido, connection);
                }

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    private int inserirTransporte(Connection connection) throws SQLException {
        String sql = "INSERT INTO Transporte (prazo_de_entrega) VALUES (NULL)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Não foi possível gerar o ID de transporte.");
                }
            }
        }
    }

    private void inserirInstalacoes(Pedido pedido, Connection connection) throws SQLException {
        System.out.println("Inserindo instalações para o pedido: " + pedido.getNumero());
        if (pedido.getInstalacoes() == null || pedido.getInstalacoes().isEmpty()) {
            System.out.println("Nenhuma instalação para processar.");
            return;
        }
        String sqlInst = "INSERT INTO Pedido_Instalacao (fk_Pedido_numero, fk_Instalacao_ID_instalacao, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmtInst = connection.prepareStatement(sqlInst)) {
            for (Instalacao inst : pedido.getInstalacoes()) {
                System.out.println("Inserindo instalação com ID: " + 4 + " para o pedido: " + pedido.getNumero());
                stmtInst.setInt(1, pedido.getNumero());
                stmtInst.setInt(2, 4);
                stmtInst.setInt(3, inst.getQuantidade());
                stmtInst.executeUpdate();
            }
        }
    }

    private void inserirProdutos(Pedido pedido, Connection connection) throws SQLException {
        String sqlProd = "INSERT INTO Pedido_Produto (fk_Pedido_numero, fk_Produto_codigo, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmtProd = connection.prepareStatement(sqlProd)) {
            for (Produto prod : pedido.getProdutos()) {
                if (prod.getAtivo() != null && prod.getAtivo() && prod.getCodigo() > 0) {
                    System.out.println("Inserindo produto com código: " + prod.getCodigo());
                    stmtProd.setInt(1, pedido.getNumero());
                    stmtProd.setInt(2, prod.getCodigo());
                    stmtProd.setInt(3, prod.getQuantidade());
                    stmtProd.executeUpdate();
                } else {
                    System.out.println("Produto inválido ou inativo: Código - " + prod.getCodigo());
                }
            }
        }
    }

    public double findTotalVendas() {
        String sql = "SELECT SUM(valor_total) FROM Pedido";
        return db.queryForObject(sql, Double.class);
    }

    public List<VendaMensalDTO> findVendasMensais() {
        String sql = "SELECT DATE_FORMAT(data_de_realizacao, '%Y-%m') AS mes, SUM(valor_total) AS valor " +
                    "FROM Pedido " +
                    "GROUP BY DATE_FORMAT(data_de_realizacao, '%Y-%m') " +
                    "ORDER BY mes";

        return db.query(sql, (rs, rowNum) ->
            new VendaMensalDTO(
                rs.getString("mes"),
                rs.getDouble("valor")
            )
        );
    }

    public List<PedidoDetalhadoDTO> listarPedidosDetalhados() {
        String sql =
            "SELECT " +
            "   p.numero AS idPedido, " +
            "   c.nome AS nomeCliente, " +
            "   c.cod_cliente AS idCliente, " +
            "   pr.codigo AS idProduto, " +
            "   pr.nome AS nomeProduto, " +
            "   pr.descricao AS detalheProduto, " +
            "   pr.marca AS marcaProduto, " +
            "   pp.quantidade AS quantidadeProduto, " +
            "   CASE WHEN pi.fk_Pedido_numero IS NOT NULL THEN 'Sim' ELSE 'Não' END AS instalacao, " +
            "   COALESCE(pi.quantidade, 0) AS quantidadeInstalacao, " +
            "   p.status AS statusPedido " +
            "FROM " +
            "   Pedido p " +
            "JOIN Cliente c ON p.fk_Cliente_cod_cliente = c.cod_cliente " +
            "JOIN Pedido_Produto pp ON p.numero = pp.fk_Pedido_numero " +
            "JOIN Produto pr ON pp.fk_Produto_codigo = pr.codigo " +
            "LEFT JOIN Pedido_Instalacao pi ON p.numero = pi.fk_Pedido_numero";

        return db.query(sql, new BeanPropertyRowMapper<>(PedidoDetalhadoDTO.class));
    }
}