package com.ijb.beneficiarios_api.dtos.beneficiario;

import java.util.Date;

public record MembroFamiliarDTO(String nome, String sobrenome, String cpf, Date dtNascimento, float valorRenda, String origemRenda, String problemasSaude) {
}
