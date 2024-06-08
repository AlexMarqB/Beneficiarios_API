package com.ijb.beneficiarios_api.dtos.triagem;

import com.ijb.beneficiarios_api.dtos.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.voluntario.VoluntarioDTO;

import java.util.Date;
import java.util.List;

public record VisitaDTO(List<VoluntarioDTO> voluntarios, BeneficiarioDTO beneficiario, Date dataVisita) {
}
