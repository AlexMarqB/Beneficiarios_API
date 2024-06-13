package com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MembroFamiliarDTO {
    private String cpf;
    private BeneficiarioDTO beneficiario;
    private String rg;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private int idade;
    private String escolaridade;
    private float renda;
    private String origemRenda;
    private String problemasSaude;
    private LocalDateTime dtCadastro;
}
