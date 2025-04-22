package com.casaclima.backend.model;

import java.sql.Date;

public class Transporte {
    private int idTransporte;
    private Date prazoDeEntrega;

    public int getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(int idTransporte) {
        this.idTransporte = idTransporte;
    }

    public Date getPrazoDeEntrega() {
        return prazoDeEntrega;
    }

    public void setPrazoDeEntrega(Date prazoDeEntrega) {
        this.prazoDeEntrega = prazoDeEntrega;
    }
}
