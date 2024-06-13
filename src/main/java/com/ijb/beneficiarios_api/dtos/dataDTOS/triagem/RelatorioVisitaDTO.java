package com.ijb.beneficiarios_api.dtos.dataDTOS.triagem;

import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario.VoluntarioDTO;

public record RelatorioVisitaDTO(Integer codRelatorio,
                                 VisitaDTO visita,
                                 BeneficiarioDTO beneficiario,
                                 VoluntarioDTO voluntario,
                                 Long descricao,
                                 String imagens) {
}
