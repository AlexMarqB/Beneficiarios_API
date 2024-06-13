package com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario;

import com.ijb.beneficiarios_api.enums.FuncoesVoluntarioEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoluntarioDTO {
    private Integer codVoluntario;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String telefone;
    private Date dtNascimento;
    private FuncoesVoluntarioEnum funcao;
    private LocalDateTime dtCadastro;
}
