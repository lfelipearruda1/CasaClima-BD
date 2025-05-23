package com.casaclima.backend.model;

import java.sql.Date;
import java.util.List;

public class Pedido {
    private int numero;
    private Date dataDeRealizacao;
    private double valorTotal;
    private String status;
    private String metodoPagamento;
    private String funcionarioMatricula;
    private int clienteId;
    private int transporteId;
    private List<Instalacao> instalacoes;
    private String enderecoRua;
    private int enderecoNumero;
    private String enderecoCidade;
    private String enderecoBairro;
    private String enderecoCep;
    private List<Produto> produtos;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDataDeRealizacao() {
        return dataDeRealizacao;
    }

    public void setDataDeRealizacao(Date dataDeRealizacao) {
        this.dataDeRealizacao = dataDeRealizacao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getFuncionarioMatricula() {
        return funcionarioMatricula;
    }

    public void setFuncionarioMatricula(String funcionarioMatricula) {
        this.funcionarioMatricula = funcionarioMatricula;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getTransporteId() {
        return transporteId;
    }

    public void setTransporteId(int transporteId) {
        this.transporteId = transporteId;
    }

    public List<Instalacao> getInstalacoes() {
        return instalacoes;
    }

    public void setInstalacoes(List<Instalacao> instalacoes) {
        this.instalacoes = instalacoes;
    }

    public String getEnderecoRua() {
        return enderecoRua;
    }

    public void setEnderecoRua(String enderecoRua) {
        this.enderecoRua = enderecoRua;
    }

    public int getEnderecoNumero() {
        return enderecoNumero;
    }

    public void setEnderecoNumero(int enderecoNumero) {
        this.enderecoNumero = enderecoNumero;
    }

    public String getEnderecoCidade() {
        return enderecoCidade;
    }

    public void setEnderecoCidade(String enderecoCidade) {
        this.enderecoCidade = enderecoCidade;
    }

    public String getEnderecoBairro() {
        return enderecoBairro;
    }

    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }

    public String getEnderecoCep() {
        return enderecoCep;
    }

    public void setEnderecoCep(String enderecoCep) {
        this.enderecoCep = enderecoCep;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}