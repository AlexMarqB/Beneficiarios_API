package com.ijb.beneficiarios_api.dtos;

import com.ijb.beneficiarios_api.enums.EstadoBeneficiarioEnum;

import java.util.Date;

public record BeneficiarioDTO(String nomeResponsavel, String cpfResponsavel, Date dtNascimentoResponsavel, String endereço, String telefone, EstadoBeneficiarioEnum estadoAtividade) {
}
