package com.ijb.beneficiarios_api.dtos.creationDTOS.voluntario;

import java.util.Date;

public record CreateVoluntarioDTO(String nome,
                                  String sobrenome,
                                  String cpf,
                                  String email,
                                  String telefone,
                                  String senha,
                                  Date dtNascimento) {
}
