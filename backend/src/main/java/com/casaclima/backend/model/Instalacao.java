package com.casaclima.backend.model;

public class Instalacao {
    private int idInstalacao;
    private double valor;
    private int quantidade;

    public int getIdInstalacao() {
        return idInstalacao;
    }

    public void setIdInstalacao(int idInstalacao) {
        this.idInstalacao = idInstalacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
