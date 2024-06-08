package com.ijb.beneficiarios_api.entities;

import java.time.LocalDateTime;

public class Usuario {
    protected LocalDateTime dataRegistro;

    public Usuario() {
        this.dataRegistro = LocalDateTime.now();
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }
}
