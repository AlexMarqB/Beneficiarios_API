package com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario;

import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.AssembleiaDTO;
import com.ijb.beneficiarios_api.enums.EstadoBeneficiarioTriagemEnum;

import java.time.LocalDateTime;
import java.util.List;

public record BeneficiarioDTO (Integer codBeneficiario,
                               String endereco,
                               String tipoResidencia,
                               String necessidadeFamiliar,
                               EstadoBeneficiarioTriagemEnum estado,
                               List<MembroFamiliarDTO> compFamiliar,
                               AssembleiaDTO assembleia,
                               LocalDateTime dtAtualizacao) {
}
