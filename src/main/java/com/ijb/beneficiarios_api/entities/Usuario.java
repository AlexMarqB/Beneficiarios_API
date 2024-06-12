package com.ijb.beneficiarios_api.entities;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Usuario {
    protected LocalDateTime dtCadastro;

    public Usuario() {
        this.dtCadastro = LocalDateTime.now();
    }
}
