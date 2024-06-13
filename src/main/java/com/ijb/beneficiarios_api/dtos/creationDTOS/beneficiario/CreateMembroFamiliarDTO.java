package com.ijb.beneficiarios_api.dtos.creationDTOS.beneficiario;

public record CreateMembroFamiliarDTO(String cpf,
                                      String rg,
                                      String nome,
                                      String sobrenome,
                                      String email,
                                      String telefone,
                                      String senha,
                                      String escolaridade,
                                      float renda,
                                      String origemRenda,
                                      String problemasSaude) {
}
