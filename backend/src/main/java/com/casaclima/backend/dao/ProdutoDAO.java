package com.casaclima.backend.dao;

import com.casaclima.backend.model.Produto;
import com.casaclima.backend.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setCodigo(rs.getInt("codigo"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setCapacidade(rs.getString("capacidade"));
                p.setPreco(rs.getDouble("preco"));
                p.setMarca(rs.getString("marca"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public Produto buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM Produto WHERE codigo = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto p = new Produto();
                    p.setCodigo(rs.getInt("codigo"));
                    p.setNome(rs.getString("nome"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setCapacidade(rs.getString("capacidade"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setMarca(rs.getString("marca"));
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO Produto (codigo, nome, descricao, capacidade, preco, marca) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produto.getCodigo());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, produto.getCapacidade());
            stmt.setDouble(5, produto.getPreco());
            stmt.setString(6, produto.getMarca());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean atualizar(Produto produto) {
        String sql = "UPDATE Produto SET nome=?, descricao=?, capacidade=?, preco=?, marca=? WHERE codigo=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getCapacidade());
            stmt.setDouble(4, produto.getPreco());
            stmt.setString(5, produto.getMarca());
            stmt.setInt(6, produto.getCodigo());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletar(int codigo) {
        String sql = "DELETE FROM Produto WHERE codigo=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}