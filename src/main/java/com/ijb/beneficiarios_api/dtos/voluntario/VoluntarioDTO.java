package com.ijb.beneficiarios_api.dtos;

import java.util.Date;

public record VoluntarioDTO(String nome, String sobrenome, String cpf, String rg, String email, String telefone, Date dataNascimento) {
}
