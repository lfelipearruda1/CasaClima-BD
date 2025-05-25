package com.casaclima.backend.dto;

public class VendaMensalDTO {
    private String mes;
    private double valor;

    public VendaMensalDTO(String mes, double valor) {
        this.mes = mes;
        this.valor = valor;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}