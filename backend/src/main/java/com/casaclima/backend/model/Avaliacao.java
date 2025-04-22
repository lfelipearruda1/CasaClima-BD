package com.casaclima.backend.model;

import java.sql.Date;

public class Avaliacao {
    private int nota;
    private String descricao;
    private Date data;
    private int fkClienteCodCliente;
    private int fkProdutoCodigo;

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getFkClienteCodCliente() {
        return fkClienteCodCliente;
    }

    public void setFkClienteCodCliente(int fkClienteCodCliente) {
        this.fkClienteCodCliente = fkClienteCodCliente;
    }

    public int getFkProdutoCodigo() {
        return fkProdutoCodigo;
    }

    public void setFkProdutoCodigo(int fkProdutoCodigo) {
        this.fkProdutoCodigo = fkProdutoCodigo;
    }
}
