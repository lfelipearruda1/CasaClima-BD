package com.casaclima.backend.dto;

public class PedidoDetalhadoDTO {
    private int idPedido;
    private String nomeCliente;
    private int idCliente;
    private int idProduto;
    private String nomeProduto;
    private String detalheProduto;
    private String marcaProduto;
    private int quantidadeProduto;
    private String instalacao;
    private int quantidadeInstalacao;
    private String statusPedido;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDetalheProduto() {
        return detalheProduto;
    }

    public void setDetalheProduto(String detalheProduto) {
        this.detalheProduto = detalheProduto;
    }

    public String getMarcaProduto() {
        return marcaProduto;
    }

    public void setMarcaProduto(String marcaProduto) {
        this.marcaProduto = marcaProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public String getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(String instalacao) {
        this.instalacao = instalacao;
    }

    public int getQuantidadeInstalacao() {
        return quantidadeInstalacao;
    }

    public void setQuantidadeInstalacao(int quantidadeInstalacao) {
        this.quantidadeInstalacao = quantidadeInstalacao;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }
}