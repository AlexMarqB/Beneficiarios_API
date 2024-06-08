package com.ijb.beneficiarios_api.dtos.triagem;

import com.ijb.beneficiarios_api.dtos.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.voluntario.VoluntarioDTO;

public record RelatorioVisitaDTO(VisitaDTO visita, BeneficiarioDTO beneficiario, VoluntarioDTO voluntario, String descricao) {
}
