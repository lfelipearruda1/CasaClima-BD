package com.casaclima.backend.model;

import java.util.List;

public class Carrinho {
    private int codCliente;
    private List<ItemDoCarrinho> itens;

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public List<ItemDoCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemDoCarrinho> itens) {
        this.itens = itens;
    }
}