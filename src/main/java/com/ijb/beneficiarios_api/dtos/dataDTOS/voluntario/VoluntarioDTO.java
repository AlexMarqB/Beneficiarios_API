package com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario;

import com.ijb.beneficiarios_api.enums.FuncoesVoluntarioEnum;

import java.time.LocalDateTime;
import java.util.Date;

public record VoluntarioDTO(Integer codVoluntario,
                            String nome,
                            String sobrenome,
                            String cpf,
                            String email,
                            String telefone,
                            Date dtNascimento,
                            FuncoesVoluntarioEnum funcao,
                            LocalDateTime dtCadastro) {
}
