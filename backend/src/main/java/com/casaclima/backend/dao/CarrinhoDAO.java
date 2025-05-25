package com.casaclima.backend.dao;

import com.casaclima.backend.model.Carrinho;
import com.casaclima.backend.model.ItemDoCarrinho;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarrinhoDAO {
    private final JdbcTemplate db;

    public CarrinhoDAO(JdbcTemplate db) {
        this.db = db;
    }

    // Listar itens do carrinho
    public Carrinho getCarrinho(int codCliente) {
        List<ItemDoCarrinho> itens = db.query(
            "SELECT produto_id, quantidade FROM Carrinho WHERE cod_cliente = ?",
            new BeanPropertyRowMapper<>(ItemDoCarrinho.class),
            codCliente
        );
        Carrinho carrinho = new Carrinho();
        carrinho.setCodCliente(codCliente);
        carrinho.setItens(itens);
        return carrinho;
    }

    // Adicionar item ao carrinho
    public void adicionarItem(int codCliente, ItemDoCarrinho item) {
        int count = db.queryForObject(
            "SELECT COUNT(*) FROM Carrinho WHERE cod_cliente = ? AND produto_id = ?",
            Integer.class, codCliente, item.getProdutoId()
        );

        if (count > 0) {
            db.update(
                "UPDATE Carrinho SET quantidade = quantidade + ? WHERE cod_cliente = ? AND produto_id = ?",
                item.getQuantidade(), codCliente, item.getProdutoId()
            );
        } else {
            db.update(
                "INSERT INTO Carrinho (cod_cliente, produto_id, quantidade) VALUES (?, ?, ?)",
                codCliente, item.getProdutoId(), item.getQuantidade()
            );
        }
    }

    // Remover item do carrinho
    public void removerItem(int codCliente, ItemDoCarrinho item) {
        db.update(
            "DELETE FROM Carrinho WHERE cod_cliente = ? AND produto_id = ?",
            codCliente, item.getProdutoId()
        );
    }
}