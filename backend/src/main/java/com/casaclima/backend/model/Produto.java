package com.casaclima.backend.model;

public class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private String capacidade;
    private double preco;
    private double precoOriginal = 0.0;
    private String marca;
    private Boolean ativo;
    private int quantidade;
    private double desconto;
    private String caminhoImagem;

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCapacidade() { return capacidade; }
    public void setCapacidade(String capacidade) { this.capacidade = capacidade; }

    public double getPrecoOriginal() { return precoOriginal; }
    public void setPrecoOriginal(double precoOriginal) { this.precoOriginal = precoOriginal; }

    public double getPreco() {
        if (desconto > 0) {
            return precoOriginal * (1 - desconto / 100);
        }
        return precoOriginal;
    }
    public void setPreco(double preco) { this.preco = preco; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getDesconto() { return desconto; }
    public void setDesconto(double desconto) { this.desconto = desconto; }

    public String getCaminhoImagem() { return caminhoImagem; }
    public void setCaminhoImagem(String caminhoImagem) { this.caminhoImagem = caminhoImagem; }

    public String getImagemUrl() {
        if (caminhoImagem != null && !caminhoImagem.isEmpty()) {
            return caminhoImagem;
        }
        switch (marca.toLowerCase()) {
            case "lg":
                return "/imagens/lg.jpg";
            case "hitachi":
                return "/imagens/hitachi.jpg";
            case "samsung":
                return "/imagens/samsung.jpg";
            default:
                return "/imagens/default.jpg";
        }
    }
}