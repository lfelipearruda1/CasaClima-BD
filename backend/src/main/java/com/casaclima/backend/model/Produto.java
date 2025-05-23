package com.casaclima.backend.model;

public class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private String capacidade;
    private double preco;
    private String marca;
    private Boolean ativo;
    private int quantidade;


    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCapacidade() { return capacidade; }
    public void setCapacidade(String capacidade) { this.capacidade = capacidade; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public int getQuantidade() { return quantidade; } 
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; } 
}
