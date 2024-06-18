package com.ijb.beneficiarios_api.dtos.creationDTOS.triagem;

import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.VisitaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario.VoluntarioDTO;

public record CreateRelatorioVisitaDTO(VisitaDTO visita,
                                       BeneficiarioDTO beneficiario,
                                       VoluntarioDTO voluntario,
                                       Long descricao,
                                       String imagens) {
}
