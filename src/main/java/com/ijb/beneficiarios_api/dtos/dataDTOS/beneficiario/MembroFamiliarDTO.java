package com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario;

import java.time.LocalDateTime;

public record MembroFamiliarDTO (String cpf,
                                 BeneficiarioDTO beneficiario,
                                 String rg,
                                 String nome,
                                 String sobrenome,
                                 String email,
                                 String telefone,
                                 int idade,
                                 String escolaridade,
                                 float renda,
                                 String origemRenda,
                                 String problemasSaude,
                                 LocalDateTime dtCadastro) {
}
