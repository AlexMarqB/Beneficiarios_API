package com.ijb.beneficiarios_api.entities;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Usuario {
    protected LocalDateTime dataRegistro;

    public Usuario() {
        this.dataRegistro = LocalDateTime.now();
    }
}
